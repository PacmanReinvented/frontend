package Interfaces;

import Interfaces.enums.InputTypes;
import enums.MoveDirection;
import enums.TileType;

public interface IPacmanClient {
    void updateCanvas(TileType[][] grid);
    void updateScoreboard(String[] scoreBoard);
    void handleInput(MoveDirection inputType);
    void setID(int playerNr);
    void sendScoreList(String[] scores);
    int getID();
}
