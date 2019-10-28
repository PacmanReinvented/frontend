package Interfaces;

import Enums.TileType;
import Interfaces.enums.*;

public interface ILogicGui {
    void updateCanvas(TileType[][] grid);
    void updateScoreboard(String[] scoreBoard);
    void handleInput(InputTypes inputType);
}
