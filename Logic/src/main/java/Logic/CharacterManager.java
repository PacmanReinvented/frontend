package Logic;

import Enums.MoveDirection;
import Enums.TileType;
import Interfaces.IGuiLogic;
import Models.Character;

import java.io.IOException;

public class CharacterManager implements IGuiLogic
{
    private Character character;

    public CharacterManager(Character character)
    {
        this.character = character;
    }

    public CharacterManager() {

    }

    @Override
    public void Move(MoveDirection direction, int playerNr) {
        System.out.println("Movement "+direction+" has not been handled yet.");
        //TODO send this to the proper character
    }

    @Override
    public void EndGame() {

    }

    @Override
    public TileType[][] StartGame() throws IOException {
        return MapReaderWriter.getMapFromFile("Logic\\src\\main\\java\\Logic\\resources\\test.csv");
    }

    @Override
    public void PauzeGame() {

    }
}
