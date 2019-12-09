package SocketServer;

import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import Logic.CharacterManager;
import com.google.gson.Gson;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint(value = "/platform/")
public class CommunicatorServerWebSocketEndpoint {

    // All sessions
    private static Map<Session, ILogicGui> sessionIPlatformGameClientMap = new HashMap<>();

    // Map each property to list of sessions that are subscribed to that property
    private static IGuiLogic gameServer = new CharacterManager();

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        /*
        ILogicGui responseClient = new GameServerMessageSender(session);
        sessionIPlatformGameClientMap.put(session, responseClient);
        responseClient.setPlayerNr(sessionIPlatformGameClientMap.size());
         */
        System.out.println("[#sessions]: " + sessionIPlatformGameClientMap.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessionIPlatformGameClientMap.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        /*
        GameClientMessageType messageType = gson.fromJson(jsonMessage, PlatformGameMessage.class).getMessageType();
        IPlatformGameClient client = sessionIPlatformGameClientMap.get(session);
        switch (messageType) {
            case Login:
                PlatformGameMessageLogin messageLogin = gson.fromJson(jsonMessage, PlatformGameMessageLogin.class);
                gameServer.loginPlayer(messageLogin.getName(), messageLogin.getPassword(), client);
                break;
            case Register:
                PlatformGameMessageRegister messageRegister = gson.fromJson(jsonMessage, PlatformGameMessageRegister.class);
                gameServer.registerPlayer(messageRegister.getName(), messageRegister.getPassword(), client);
                break;
            case Input:
                PlatformGameMessageInput messageInput = gson.fromJson(jsonMessage, PlatformGameMessageInput.class);
                gameServer.receiveInput(messageInput.getInputType(), client);
                break;
            case StartGame:
                PlatformGameMessageStart messageStart = gson.fromJson(jsonMessage, PlatformGameMessageStart.class);
                gameServer.startGame(client);
                break;
        }

         */
    }
}