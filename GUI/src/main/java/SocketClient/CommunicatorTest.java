package SocketClient;


import Interfaces.IGuiLogic;

public class CommunicatorTest {

    public static void main(String[] args) {
        IGuiLogic server = new GameClientMessageSender();
        server.registerPlayer(null,"mr test");
    }
}
