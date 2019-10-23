package Interfaces;

import Enums.TileType;

public interface ILogicGui {
    void updateCanvas(TileType[][] grid);
    void updateScoreboard(String[] scoreBoard);
}
