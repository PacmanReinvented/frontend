package SocketClient;

import Interfaces.IPacmanClient;
import SocketMessage.*;
import com.google.gson.Gson;
import javafx.application.Platform;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@ClientEndpoint
public class CommunicatorClientWebSocketEndpoint implements ICommunicator {

    // Singleton
    private static CommunicatorClientWebSocketEndpoint instance = null;

    /**
     * The local websocket uri to connect to.
     */
    private final String uri = "ws://localhost:8095/pacman/";

    private Session session;

    private String message;

    private Gson gson = null;

    private IPacmanClient pacmanClient;

    // Status of the webSocket client
    boolean isRunning = false;

    // Private constructor (singleton pattern)
    private CommunicatorClientWebSocketEndpoint() {
        gson = new Gson();
    }

    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     *
     * @return instance of client web socket
     */
    public static CommunicatorClientWebSocketEndpoint getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new CommunicatorClientWebSocketEndpoint();
        }
        return instance;
    }

    /**
     * Start the connection.
     */
    @Override
    public void start() {
        System.out.println("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    @Override
    public void stop() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        System.out.println("[WebSocket Client open session] " + session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session) {
        this.message = message;
        System.out.println("[WebSocket Client message received] " + message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[WebSocket Client connection error] " + cause.toString());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }


    @Override
    public void sendMessage(SocketMessage message) {
        sendMessageToServer(message);
    }

    @Override
    public void setGameClient(IPacmanClient pacmanGameClient) {
        this.pacmanClient = pacmanGameClient;
    }


    private void sendMessageToServer(SocketMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }

    /**
     * Get the latest message received from the websocket communication.
     *
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        System.out.println("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (IOException | URISyntaxException | DeploymentException ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient() {
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    // Process incoming json message
    private void processMessage(String jsonMessage) {

        // Parse incoming message
        SocketOperationType messageType = null;
        System.out.println("Message received: " + jsonMessage);
        SocketOperationType operationType = gson.fromJson(jsonMessage, SocketMessage.class).getOperationType();

        switch (operationType) {
            case POSITIONS:
                SocketResponsePositionMessage positionMessage = gson.fromJson(jsonMessage, SocketResponsePositionMessage.class);
                Platform.runLater(()-> {
                pacmanClient.updateCanvas(positionMessage.getPositions());
                });
                break;
            case GAMESTATE:
                SocketResponseGameStateMessage gameStateMessage = gson.fromJson(jsonMessage, SocketResponseGameStateMessage.class);
                System.out.println("[CommunicatorClientWebSocketEndpoint.java] Updating gamestate not yet supported on client");
                break;
            case SCORE:
                SocketResponseScoreMessage scoreMessage = gson.fromJson(jsonMessage, SocketResponseScoreMessage.class);
                Platform.runLater(()-> {
                pacmanClient.updateScoreboard(scoreMessage.getScores());
                });
                break;
            case LOGINRESPONSE:
                SocketResponseLoginMessage loginMessage = gson.fromJson(jsonMessage, SocketResponseLoginMessage.class);
                Platform.runLater(()-> {
                    pacmanClient.receiveLoginState(loginMessage.getLoginState(), loginMessage.getName());
                });
                break;
            case REGISTERRESPONSE:
                SocketResponseRegisterMessage registerMessage = gson.fromJson(jsonMessage, SocketResponseRegisterMessage.class);
                Platform.runLater(()-> {
                    pacmanClient.receiveRegisterState(registerMessage.getRegisterState());
                });
        }
    }
}
