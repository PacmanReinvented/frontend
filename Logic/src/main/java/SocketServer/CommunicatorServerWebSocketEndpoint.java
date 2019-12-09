package SocketServer;
import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import Logic.CharacterManager;
import SocketMessage.*;
import com.google.gson.Gson;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/pacman/")
public class CommunicatorServerWebSocketEndpoint {

    // All sessions
    private static Map<Session, IPacmanClient> sessionIPacmanClient = new HashMap<>();

    // Map each property to list of sessions that are subscribed to that property
    private static IPacmanServer gameServer = new CharacterManager();

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        IPacmanClient responseClient = new GameServerMessageSender(session);
        sessionIPacmanClient.put(session, responseClient);
        responseClient.setID(sessionIPacmanClient.size());
        System.out.println("[#sessions]: " + sessionIPacmanClient.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessionIPacmanClient.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        IPacmanClient client = sessionIPacmanClient.get(session);
        SocketOperationType messageType = gson.fromJson(jsonMessage, SocketMessage.class).getOperationType();
        switch (messageType){

            case INPUT:
                SocketClientInputMessage messageInput = gson.fromJson(jsonMessage, SocketClientInputMessage.class);
                gameServer.Move(messageInput.getInputType(), client);
                break;
            case REGISTER:
                SocketClientRegisterMessage registerMessage = gson.fromJson(jsonMessage, SocketClientRegisterMessage.class);
                gameServer.registerPlayer(client, registerMessage.getName());
                break;
            case STARTGAME:
                gameServer.StartGame(client);
                break;
        }

    }
}
