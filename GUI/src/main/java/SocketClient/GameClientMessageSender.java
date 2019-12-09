package SocketClient;

import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import SocketMessage.SocketClientRegisterMessage;
import enums.MoveDirection;
import enums.TileType;

import java.io.IOException;

public class GameClientMessageSender implements IGuiLogic {

    private ICommunicator communicator = CommunicatorClientWebSocketEndpoint.getInstance();

    public GameClientMessageSender(){
        communicator.start();
    }

    @Override
    public void registerPlayer(ILogicGui GUI, String name) {
        SocketClientRegisterMessage message = new SocketClientRegisterMessage(name,"frits");
        communicator.sendMessage(message);
    }

    @Override
    public void Move(MoveDirection direction, int playerNr) {
        throw new UnsupportedOperationException("method not implemented registerPlayer()");
    }

    @Override
    public void EndGame() {
        throw new UnsupportedOperationException("method not implemented registerPlayer()");
    }

    @Override
    public TileType[][] StartGame() throws IOException {
        throw new UnsupportedOperationException("method not implemented registerPlayer()");
    }

    @Override
    public void PauzeGame() {
        throw new UnsupportedOperationException("method not implemented registerPlayer()");
    }

    @Override
    public String getScoreList() {
        throw new UnsupportedOperationException("method not implemented registerPlayer()");
    }
}
