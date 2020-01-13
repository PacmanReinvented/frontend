package SocketMessage;

public enum SocketOperationType {
    //Client to server
    INPUT,
    REGISTER,
    STARTGAME,
    //Server to client
    POSITIONS,
    GAMESTATE,
    SCORE
}