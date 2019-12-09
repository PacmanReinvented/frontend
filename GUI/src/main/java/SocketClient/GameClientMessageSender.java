package SocketClient;

import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import SocketMessage.SocketClientRegisterMessage;
import enums.MoveDirection;
import enums.TileType;

import java.io.IOException;

public class GameClientMessageSender implements IPacmanServer {

    private ICommunicator communicator = CommunicatorClientWebSocketEndpoint.getInstance();

    public GameClientMessageSender(){
        communicator.start();
    }

    @Override
    public void registerPlayer(IPacmanClient GUI, String name) {
        SocketClientRegisterMessage message = new SocketClientRegisterMessage(name,"frits");
        communicator.sendMessage(message);
        communicator.setGameClient(GUI);
    }

    @Override
    public void Move(MoveDirection direction, IPacmanClient client) {
        throw new UnsupportedOperationException("Method Move not implemented");
    }

    @Override
    public void EndGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method EndGame not implemented");
    }

    @Override
    public void StartGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method StartGame not implemented");
    }

    @Override
    public void PauzeGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method PauzeGame not implemented");
    }

}
