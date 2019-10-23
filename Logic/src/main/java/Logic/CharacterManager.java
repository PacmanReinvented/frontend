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

    public CharacterManager() {

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
        return MapReaderWriter.getMapFromFile("C:\\Users\\B_sch\\IdeaProjects\\frontend\\frontend\\Logic\\src\\main\\java\\Logic\\resources\\test.csv");
    }

    @Override
    public void PauzeGame() {

    }
}
