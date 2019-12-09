package SocketMessage;

public class SocketClientStartGameMessage extends SocketMessage {
    public SocketClientStartGameMessage()
    {
        setOperationType(SocketOperationType.STARTGAME);
    }
}
