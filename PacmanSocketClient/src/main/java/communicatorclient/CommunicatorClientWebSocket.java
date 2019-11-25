package communicatorclient;

import Enums.MoveDirection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
import SocketMessage.*;
import java.io.IOException;
import java.net.URISyntaxException;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * Client-side implementation of abstract class Communicator using 
 * WebSockets for communication.
 * 
 * This code is based on the example code from:
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers
 */
@ClientEndpoint
public class CommunicatorClientWebSocket extends Communicator {
    
    // Singleton
    private static CommunicatorClientWebSocket instance = null;
    
    /**
     * The local websocket uri to connect to.
     */
    private final String uri = "ws://localhost:8095/communicator/";
    
    private Session session;

    private String message;
    
    private Gson gson = null;
    
    // Status of the webSocket client
    boolean isRunning = false;
    
    // Private constructor (singleton pattern)
    private CommunicatorClientWebSocket() {
        gson = new Gson();
    }
    
    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static CommunicatorClientWebSocket getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new CommunicatorClientWebSocket();
        }
        return instance;
    }

    /**
     *  Start the connection.
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
    public void onWebSocketConnect(Session session){
        System.out.println("[WebSocket Client open session] " + session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        this.message = message;
        System.out.println("[WebSocket Client message received] " + message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[WebSocket Client connection error] " + cause.toString());
    }
    
    @OnClose
    public void onWebSocketClose(CloseReason reason){
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }

    @Override
    public void registerUser(String name, String password) {
        SocketClientRegisterMessage message = new SocketClientRegisterMessage(name,password);
        sendMessageToServer(message);
    }

    @Override
    public void sendInput(MoveDirection input) {
        SocketClientMessage message = new SocketClientMessage(input);
        sendMessageToServer(message);
    }


    private void sendMessageToServer(SocketMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }
    
    /**
     * Get the latest message received from the websocket communication.
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
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
    private void stopClient(){
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }
    
    // Process incoming json message
    private void processMessage(String jsonMessage) {
        
        // Parse incoming message
        SocketMessage wsMessage;
        try {
            wsMessage = gson.fromJson(jsonMessage, SocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
            return;
        }
        
        // Only operation update property will be further processed
        SocketOperationType operation;
        operation = wsMessage.getOperationType();
        if (operation == null ) {
            System.out.println("[WebSocket Client ERROR: Response Message Type invalid.]");
            return;
        }

        SocketMessage socketMessage;
        //TODO make this an entity factory
        switch (operation){
            case POSITIONS:
                try {
                    socketMessage = gson.fromJson(jsonMessage, SocketResponsePositionMessage.class);
                }
                catch (JsonSyntaxException ex) {
                    System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
                    return;
                }
                break;
            case GAMESTATE:
                try {
                    socketMessage = gson.fromJson(jsonMessage, SocketResponseGameStateMessage.class);
                }
                catch (JsonSyntaxException ex) {
                    System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
                    return;
                }
                break;
            default:
                System.out.println("[WebSocket Client ERROR: Received invalid type of response message "+operation);
                return;
        }
        // Notify observers
        this.setChanged();
        this.notifyObservers(socketMessage);
    }
}
