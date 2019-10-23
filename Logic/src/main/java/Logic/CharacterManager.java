package Logic;

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


    @Override
    public void MoveLeft() {

    }

    @Override
    public void MoveRight() {

    }

    @Override
    public void MoveUp() {

    }

    @Override
    public void MoveDown() {

    }

    @Override
    public void EndGame() {

    }

    @Override
    public TileType[][] StartGame() throws IOException {
        return MapReaderWriter.getMapFromFile("file:Logic\\src\\main\\java\\resources\\test.csv");
    }

    @Override
    public void PauzeGame() {

    }
}
