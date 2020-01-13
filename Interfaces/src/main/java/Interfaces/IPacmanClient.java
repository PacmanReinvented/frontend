package Interfaces;

import Enums.LoginState;
import Enums.RegisterState;
import Interfaces.enums.InputTypes;
import enums.GameState;
import enums.MoveDirection;
import enums.TileType;

public interface IPacmanClient {
    void updateCanvas(TileType[][] grid);
    void updateScoreboard(String[] scoreBoard);
    void handleInput(MoveDirection inputType);
    void setID(int playerNr);
    void sendScoreList(String[] scores);
    void receiveGameState(GameState gameState);
    void receiveLoginState(LoginState loginState, String username);
    void receiveRegisterState(RegisterState registerState);
    int getID();
}
