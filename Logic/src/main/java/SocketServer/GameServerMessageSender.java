package SocketServer;

import Enums.LoginState;
import Enums.RegisterState;
import Interfaces.IPacmanClient;
import SocketMessage.*;
import com.google.gson.Gson;
import enums.GameState;
import enums.MoveDirection;
import enums.TileType;

import javax.websocket.Session;

public class GameServerMessageSender implements IPacmanClient {

    private Session session;
    private Gson gson;
    int playerID;

    public GameServerMessageSender(Session session){
        this.session = session;
        gson = new Gson();
    }

    @Override
    public void updateCanvas(TileType[][] grid) {
        SocketResponsePositionMessage message = new SocketResponsePositionMessage(grid);
        sendMessage(message);
    }

    @Override
    public void updateScoreboard(String[] scoreBoard) {

    }

    @Override
    public void handleInput(MoveDirection inputType) {
        throw new UnsupportedOperationException("THis method is not supposed to be called from the server!");
    }

    @Override
    public void setID(int playerNr) {
        this.playerID = playerNr;
    }

    @Override
    public void sendScoreList(String[] scores) {
        SocketResponseScoreMessage message = new SocketResponseScoreMessage(scores);
        sendMessage(message);
    }

    @Override
    public void receiveGameState(GameState gameState) {
        SocketResponseGameStateMessage message = new SocketResponseGameStateMessage();
        message.setGameState(gameState);
        sendMessage(message);
    }

    @Override
    public void receiveLoginState(LoginState loginState, String username) {
        SocketResponseLoginMessage message = new SocketResponseLoginMessage();
        message.setLoginState(loginState);
        message.setName(username);
        sendMessage(message);
    }

    @Override
    public void receiveRegisterState(RegisterState registerState) {
        SocketResponseRegisterMessage message = new SocketResponseRegisterMessage();
        message.setRegisterState(registerState);
        sendMessage(message);
    }

    @Override
    public int getID() {
        return playerID;
    }

    private void sendMessage(SocketMessage message){
        String json = gson.toJson(message);
        session.getAsyncRemote().sendText(json);
    }
}
