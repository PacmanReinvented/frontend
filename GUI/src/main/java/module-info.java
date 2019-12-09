module GUI {
    requires javafx.graphics;
    requires MODELS;
    requires INTERFACES;
    requires LOGIC;
    requires javafx.fxml;
    requires SOCKETSHARED;
    requires gson;
    requires java.sql;
    requires javafx.controls;
    requires javax.websocket.api;
    opens UI;
    exports UI;
    opens SocketClient;
    exports SocketClient;
}