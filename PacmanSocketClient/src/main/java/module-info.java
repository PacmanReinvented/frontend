module SocketClient {
    requires javax.websocket.client.api;
    requires gson;
    requires SocketShared;
    requires PacmanReinvented;
    opens communicatorclient;
    exports communicatorclient;

}