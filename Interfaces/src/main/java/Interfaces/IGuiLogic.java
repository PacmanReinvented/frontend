package Interfaces;

import Enums.TileType;

public interface IGuiLogic {
    public void MoveLeft();
    public void MoveRight();
    public void MoveUp();
    public void MoveDown();
    public void EndGame();
    public TileType[][] StartGame();
    public void PauzeGame();
}
