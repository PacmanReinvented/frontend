package Interfaces;

import enums.MoveDirection;
import enums.TileType;

import java.io.IOException;

public interface IPacmanServer {
    void registerPlayer(IPacmanClient GUI, String name);
    void Move(MoveDirection direction, IPacmanClient client);
    void EndGame(IPacmanClient client);
    void StartGame(IPacmanClient client);
    void PauseGame(IPacmanClient client);
}

