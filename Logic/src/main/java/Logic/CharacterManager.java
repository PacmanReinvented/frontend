package Logic;

import Enums.GameState;
import Enums.MoveDirection;
import Enums.TileType;
import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import Models.Game;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

//An implementation of IGUILogic for the standalone version. To be depricated when the server is built
public class CharacterManager implements IGuiLogic, Observer {

    Game game;
    ILogicGui GUI;


    public CharacterManager() {
        game = new Game();
        game.addObserver(this);
    }

    @Override
    public void registerPlayer(ILogicGui GUI, String name) {
        this.GUI = GUI;
        System.out.println("Registred " + name + " with GUI:" + GUI);
        int playerNr = new Random().nextInt();
        game.registerPlayer(playerNr);
        GUI.setID(playerNr);
    }

    @Override
    public void Move(MoveDirection direction, int playerNr) {
        game.moveCharacter(playerNr, direction);
        //TODO send this to the proper character
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
        System.out.println("Received update from Game");
        switch ((GameState) o) {
            case STARTED:
                break;
            case PAUSED:
                break;
            case ALLITEMSEATEN:
            case PACMANDIED:
                game.startGame();
                updateGUI();
                break;
            case ENDED:
                break;
        }
    }
}
