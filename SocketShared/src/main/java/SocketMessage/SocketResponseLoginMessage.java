package SocketMessage;

import Enums.LoginState;

public class SocketResponseLoginMessage extends SocketMessage {
    private LoginState loginState;



    String name;

    public SocketResponseLoginMessage(){setOperationType(SocketOperationType.LOGINRESPONSE);};
    public SocketResponseLoginMessage(LoginState loginState, String name){
        this.loginState = loginState;
        this.name = name;
        setOperationType(SocketOperationType.LOGINRESPONSE);
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
