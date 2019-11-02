package Interfaces;

import Enums.MoveDirection;
import Enums.TileType;

import java.io.IOException;

public interface IGuiLogic {
    void registerPlayer(ILogicGui GUI, String name);
    void Move(MoveDirection direction, int playerNr);
    void EndGame();
    TileType[][] StartGame() throws IOException;
    void PauzeGame();
    String getScoreList();
}
