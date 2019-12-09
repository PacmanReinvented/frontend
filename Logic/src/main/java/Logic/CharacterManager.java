package Logic;

import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import classes.Game;
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

    private List<IPacmanClient> joinedClients = new ArrayList<>();


    public CharacterManager() {
        game = new Game();
        game.addObserver(this);
    }

    @Override
    public void registerPlayer(IPacmanClient GUI, String name) {
        joinedClients.add(GUI);

        Random r = new Random();
        int playerNr = GUI.getID();
        System.out.println("Registered " + name + " with GUI:" + GUI + " as Nr: " + playerNr);
        playerNames.put(playerNr, name);
        game.registerPlayer(playerNr);
        /*
        int computerPlayerNr;
        for (int i = 0; i < amountOfComputerPlayers; i++) {
            computerPlayerNr = r.nextInt();//the chance for a match is not big enough for me to bother to implement that check for this iteration
            game.registerPlayer(computerPlayerNr);
            computerPlayers.add(computerPlayerNr);
            playerNames.put(computerPlayerNr, "Computer Player #" + i + 1);
        }
         */
    }

    @Override
    public void Move(MoveDirection direction, IPacmanClient client) {
        game.moveCharacter(client.getID(), direction);
        Random r = new Random();

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
        while (it.hasNext()) {
            scores = new String[scorelist.size()];
            int key = (Integer) it.next();
            int score = scorelist.get(key);
            String name = playerNames.get(key);
            scores[i] = name + ": " + score;
            i++;
        }
        for (IPacmanClient joinedClient : joinedClients) {
            joinedClient.sendScoreList(scores);
        }
    }

    @Override
    public void EndGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method ENdGame not implemented");
    }

    @Override
    public void StartGame(IPacmanClient client) {
        TileType[][] tiles = new TileType[0][];
        try {
            tiles = MapReaderWriter.getMapFromFile("Logic\\src\\main\\java\\Logic\\resources\\test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.newGame(tiles);
        for (IPacmanClient joinedClient : joinedClients) {
            joinedClient.updateCanvas(tiles);
        }
    }

    @Override
    public void PauzeGame(IPacmanClient client) {
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
        switch (state) {
            case STARTED:
                break;
            case PAUSED:
                break;
            case ALLITEMSEATEN:
            case PACMANDIED:
                System.out.println("[GameManager.java] New round, because: " + state);
                game.startGame();
                updateGUI();
                //TODO send some feedback to players
                break;
            case ENDED:
                System.out.println("[CharacterManager.java] the game has ended.");
                //TODO send some feedback to players
                break;
        }
    }
}

