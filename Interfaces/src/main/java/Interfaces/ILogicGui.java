package Interfaces;

import Enums.TileType;

public interface ILogicGui {
    public void updateCanvas(TileType[][] grid);
    public void updateScoreboard(String[] scoreBoard);
}
