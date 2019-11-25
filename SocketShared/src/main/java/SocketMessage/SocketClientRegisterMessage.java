package SocketMessage;

public class SocketClientRegisterMessage extends SocketMessage {
    private String userName;
    private String password;

    public SocketClientRegisterMessage(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.setOperationType(SocketOperationType.REGISTER);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
