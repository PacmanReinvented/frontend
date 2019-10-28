package Interfaces;

import Enums.MoveDirection;
import Enums.TileType;

import java.io.IOException;

public interface IGuiLogic {
    void Move(MoveDirection direction, int playerNr);
    void EndGame();
    TileType[][] StartGame() throws IOException;
    void PauzeGame();
}
