module LOGIC {
    exports Logic;
    requires INTERFACES;
    requires MODELS;
    requires gson;
    requires javax.websocket.api;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.eclipse.jetty.websocket.javax.websocket;
    requires java.sql;
    requires SOCKETSHARED;
    exports SocketServer;
}