package Logic;

import Enums.LoginState;
import Enums.RegisterState;
import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import classes.Game;
import client.IRestTemplate;
import client.REST;
import entities.User;
import enums.GameState;
import enums.MoveDirection;
import enums.TileType;

import java.io.IOException;
import java.util.*;

//An implementation of IGUILogic for the standalone version. To be depricated when the server is built
public class CharacterManager implements IPacmanServer, Observer {

    Game game;
    private List<Integer> computerPlayers = new ArrayList<>();
    private Map<Integer, String> playerNames = new HashMap<>();
    private final int amountOfComputerPlayers = 1;
    private Random r = new Random();
    private IRestTemplate restTemplate = new REST();

    private List<IPacmanClient> joinedClients = new ArrayList<>();
    private boolean gameIsRunning = false;


    public CharacterManager() {
        newGame();
    }

    @Override
    public void loginPlayer(IPacmanClient GUI, String name, String password) {
        if(!gameIsRunning) {
            LoginState loginState = null;
            try {
                User user = restTemplate.loginUser(name, password);
                if (user != null) {
                    joinedClients.add(GUI);
                    loginState = LoginState.SUCCESS;
                    int playerNr = GUI.getID();
                    System.out.println("Registered " + name + " with GUI:" + GUI + " as Nr: " + playerNr);
                    playerNames.put(playerNr, name);
                    game.registerPlayer(playerNr);
                } else {
                    loginState = LoginState.WRONGPASS;
                }
            } catch (Exception e) {
                loginState = LoginState.WRONGUSER;
            } finally {
                GUI.receiveLoginState(loginState, name);
            }
        }
    }

    @Override
    public void registerPlayer(IPacmanClient GUI, String name, String password) {
        User user = new User(name, password);
        RegisterState registerState;
        if(restTemplate.registerUser(user)){registerState = RegisterState.SUCCESS;}
        else{registerState = RegisterState.USEREXISTS;}
        GUI.receiveRegisterState(registerState);
    }

    @Override
    public void Move(MoveDirection direction, IPacmanClient client) {
        if(joinedClients.contains(client) && gameIsRunning) {
            game.moveCharacter(client.getID(), direction);

            //"AI"
            for (Integer cpuNr : computerPlayers) {
                MoveDirection dir = MoveDirection.values()[r.nextInt(MoveDirection.values().length)];
                game.moveCharacter(cpuNr, dir);
            }
            game.updateGame();
            updateGUI();

            String[] scores = new String[0];
            Map<Integer, Integer> scorelist = game.getScoreList();
            if (scorelist == null) return;
            Iterator it = scorelist.keySet().iterator();
            int i = 0;
            scores = new String[scorelist.size()];
            while (it.hasNext()) {

                int key = (Integer) it.next();
                int score = scorelist.get(key);
                String name = playerNames.get(key);
                if (key == game.getCurrentPacman()) name += " (Pacman)";
                scores[i] = name + ": " + score;
                i++;
            }
            for (IPacmanClient joinedClient : joinedClients) {
                joinedClient.sendScoreList(scores);
            }
        }
    }

    @Override
    public void EndGame(IPacmanClient client) {
        gameIsRunning = false;
        joinedClients.clear();
        newGame();
    }

    @Override
    public void StartGame(IPacmanClient client) {
        if(!gameIsRunning) {
            TileType[][] tiles = new TileType[0][];
            try {
                tiles = MapReaderWriter.getMapFromFile("Logic\\src\\main\\java\\Logic\\resources\\level2.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            game.newGame(tiles);
            for (IPacmanClient joinedClient : joinedClients) {
                joinedClient.updateCanvas(tiles);
            }
            gameIsRunning = true;
        }
    }

    @Override
    public void PauseGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method pauseGame not implemented");
    }


    private void updateGUI() {
        for (IPacmanClient joinedClient : joinedClients) {
            joinedClient.updateCanvas(game.getTilesFromState());
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("[GameManager.java] Received update from Game");
        GameState state = (GameState) o;
        for (IPacmanClient joinedClient : joinedClients) {
            joinedClient.receiveGameState(state);
        }
        switch (state) {
            case STARTED:
                break;
            case ALLITEMSEATEN:
            case PACMANDIED:
                System.out.println("[GameManager.java] New round, because: " + state);
                game.newRound();
                updateGUI();
                break;
            case ENDED:
                System.out.println("[CharacterManager.java] the game has ended.");
                joinedClients.clear();
                break;
        }
    }
    void newGame(){
        game = new Game();
        game.addObserver(this);
    }
}

