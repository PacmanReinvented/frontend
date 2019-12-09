package Interfaces;

import enums.MoveDirection;
import enums.TileType;

import java.io.IOException;

public interface IGuiLogic {
    void registerPlayer(ILogicGui GUI, String name);
    void Move(MoveDirection direction, int playerNr);
    void EndGame();
    TileType[][] StartGame() throws IOException;
    void PauzeGame();
    String getScoreList();
}

