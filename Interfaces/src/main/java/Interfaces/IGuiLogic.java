package Interfaces;

import Enums.TileType;

import java.io.IOException;

public interface IGuiLogic {
    void MoveLeft();
    void MoveRight();
    void MoveUp();
    void MoveDown();
    void EndGame();
    TileType[][] StartGame() throws IOException;
    void PauzeGame();
}
