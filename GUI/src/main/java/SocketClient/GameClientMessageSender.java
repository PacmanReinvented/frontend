package SocketClient;

import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import SocketMessage.SocketClientInputMessage;
import SocketMessage.SocketClientLoginMessage;
import SocketMessage.SocketClientRegisterMessage;
import SocketMessage.SocketClientStartGameMessage;
import enums.MoveDirection;

public class GameClientMessageSender implements IPacmanServer {

    private ICommunicator communicator = CommunicatorClientWebSocketEndpoint.getInstance();

    public GameClientMessageSender(){
        communicator.start();
    }

    @Override
    public void loginPlayer(IPacmanClient GUI, String name, String password) {
        SocketClientLoginMessage message = new SocketClientLoginMessage(name,password);
        communicator.sendMessage(message);
        communicator.setGameClient(GUI);
    }

    @Override
    public void registerPlayer(IPacmanClient GUI, String name, String password) {
        SocketClientRegisterMessage message = new SocketClientRegisterMessage(name, password);
        communicator.sendMessage(message);
        communicator.setGameClient(GUI);
    }


    @Override
    public void Move(MoveDirection direction, IPacmanClient client) {
        SocketClientInputMessage message = new SocketClientInputMessage(direction);
        communicator.sendMessage(message);
    }

    @Override
    public void EndGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method EndGame not implemented");
    }

    @Override
    public void StartGame(IPacmanClient client) {
        SocketClientStartGameMessage message = new SocketClientStartGameMessage();
        communicator.sendMessage(message);
    }

    @Override
    public void PauseGame(IPacmanClient client) {
        throw new UnsupportedOperationException("Method PauseGame not implemented");
    }

}
