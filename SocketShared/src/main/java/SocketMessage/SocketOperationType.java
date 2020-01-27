package SocketMessage;

public enum SocketOperationType {
    //Client to server
    INPUT,
    LOGIN,
    REGISTER,
    STARTGAME,
    //Server to client
    POSITIONS,
    GAMESTATE,
    SCORE,
    LOGINRESPONSE,
    REGISTERRESPONSE,
}