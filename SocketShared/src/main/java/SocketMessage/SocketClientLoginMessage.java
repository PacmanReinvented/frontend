package SocketMessage;

public class SocketClientLoginMessage extends SocketMessage{

    private String name;
    private String password;


    public SocketClientLoginMessage(String name, String password) {
        setOperationType(SocketOperationType.LOGIN);
        this.name = name;
        this.password = password;
    }

    public SocketClientLoginMessage(){
        setOperationType(SocketOperationType.LOGIN);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
