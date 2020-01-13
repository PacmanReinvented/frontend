package Interfaces;

import enums.MoveDirection;
import enums.TileType;

import java.io.IOException;

public interface IPacmanServer {
    void loginPlayer(IPacmanClient GUI, String name, String password);
    void registerPlayer(IPacmanClient GUI, String name, String password);
    void Move(MoveDirection direction, IPacmanClient client);
    void EndGame(IPacmanClient client);
    void StartGame(IPacmanClient client);
    void PauseGame(IPacmanClient client);
}

