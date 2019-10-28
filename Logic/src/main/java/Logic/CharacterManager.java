package Logic;

import Enums.MoveDirection;
import Enums.TileType;
import Interfaces.IGuiLogic;
import Models.Game;

import java.io.IOException;

public class CharacterManager implements IGuiLogic {

    Game game;


    public CharacterManager() {

    }

    @Override
    public void Move(MoveDirection direction, int playerNr) {
        System.out.println("[CHARACTERMANAGER] Movement " + direction + " has not been handled yet. Player = " + playerNr);
        game.moveCharacter(playerNr,direction);
        //TODO send this to the proper character
    }

    @Override
    public void EndGame() {

    }

    @Override
    public TileType[][] StartGame() throws IOException {
        TileType[][] tiles = MapReaderWriter.getMapFromFile("Logic\\src\\main\\java\\Logic\\resources\\test.csv");
        game = new Game();
        game.newGame(tiles);
        return tiles;
    }

    @Override
    public void PauzeGame() {

    }
}
