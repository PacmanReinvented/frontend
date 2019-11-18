module SocketShared {
    requires Interfaces;
    requires PacmanReinvented;
    opens SocketMessage;
    exports SocketMessage;
}