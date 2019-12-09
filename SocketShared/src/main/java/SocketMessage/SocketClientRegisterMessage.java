package SocketMessage;

public class SocketClientRegisterMessage extends SocketMessage{

    private String name;
    private String password;


    public SocketClientRegisterMessage(String name, String password) {
        setOperationType(SocketOperationType.REGISTER);
        this.name = name;
        this.password = password;
    }

    public SocketClientRegisterMessage(){
        setOperationType(SocketOperationType.REGISTER);
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
