package SocketClient;

import Interfaces.IPacmanServer;

public class CommunicatorTest {

    public static void main(String[] args) {
        IPacmanServer server = new GameClientMessageSender();
        server.registerPlayer(null,"mr test");
    }
}