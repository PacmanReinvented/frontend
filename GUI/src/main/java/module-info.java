module GUI
{
    requires PacmanReinvented;
    requires Interfaces;
    requires javafx.fxml;
    requires javafx.graphics;
    requires SocketShared;
    requires gson;
    requires java.sql;
    requires javax.websocket.client.api;
    opens SocketClient;
    exports SocketClient;
    opens UI;
    exports UI;
}