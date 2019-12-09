module SOCKETSHARED {
    requires MODELS;
    opens SocketMessage;
    exports SocketMessage;
}