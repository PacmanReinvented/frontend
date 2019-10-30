package Logic;

import Enums.GameState;
import Enums.MoveDirection;
import Enums.TileType;
import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import Models.Game;

import java.io.IOException;
import java.util.*;

//An implementation of IGUILogic for the standalone version. To be depricated when the server is built
public class CharacterManager implements IGuiLogic, Observer {

    Game game;
    ILogicGui GUI;

    private List<Integer> computerPlayers = new ArrayList<>();
    private final int amountOfComputerPlayers = 1;


    public CharacterManager() {
        game = new Game();
        game.addObserver(this);
    }

    @Override
    public void registerPlayer(ILogicGui GUI, String name) {
        this.GUI = GUI;

        Random r = new Random();
        int playerNr =r.nextInt();
        System.out.println("Registered " + name + " with GUI:" + GUI + " as Nr: "+playerNr);
        game.registerPlayer(playerNr);
        int computerPlayerNr;
        for (int i = 0; i < amountOfComputerPlayers; i++) {
            computerPlayerNr = r.nextInt();//the chance for a match is not big enough for me to bother to implement that check for this iteration
            game.registerPlayer(computerPlayerNr);
            computerPlayers.add(computerPlayerNr);
        }
        GUI.setID(playerNr);
    }

    @Override
    public void Move(MoveDirection direction, int playerNr) {
        game.moveCharacter(playerNr, direction);
        Random r = new Random();

        //"AI"
        for (Integer cpuNr : computerPlayers){
            MoveDirection dir = MoveDirection.values()[r.nextInt(MoveDirection.values().length)];
            game.moveCharacter(cpuNr,dir);
        }
        updateGUI();
    }

    @Override
    public void EndGame() {

    }

    @Override
    public TileType[][] StartGame() throws IOException {
        TileType[][] tiles = MapReaderWriter.getMapFromFile("Logic\\src\\main\\java\\Logic\\resources\\oneItem.csv");

        game.newGame(tiles);
        return tiles;
    }

    @Override
    public void PauzeGame() {

    }

    private void updateGUI() {
        GUI.updateCanvas(game.getTilesFromState());
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
                System.out.println("[GameManager.java] New round, because: "+state);
                game.startGame();
                updateGUI();
                break;
            case ENDED:
                System.out.println("[CharacterManager.java] the game has ended.");
                break;
        }
    }
}
