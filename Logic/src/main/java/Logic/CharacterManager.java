package Logic;

import Enums.MoveDirection;
import Enums.TileType;
import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import Models.Game;

import java.io.IOException;

//An implementation of IGUILogic for the standalone version. To be depricated when the server is built
public class CharacterManager implements IGuiLogic {

    Game game;
    ILogicGui GUI;


    public CharacterManager() {

    }

    @Override
    public void registerPlayer(ILogicGui GUI, String name) {
        this.GUI = GUI;
        System.out.println("Registred " + name + " with GUI:" + GUI);

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
        TileType[][] tiles = MapReaderWriter.getMapFromFile("Logic\\src\\main\\java\\Logic\\resources\\empty.csv");
        game = new Game();
        game.newGame(tiles);
        return tiles;
    }

    @Override
    public void PauzeGame() {

    }

    private void updateGUI(){
        GUI.updateCanvas(game.getTilesFromState());
    }
}
