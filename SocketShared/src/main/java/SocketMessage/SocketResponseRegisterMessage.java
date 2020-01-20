package SocketMessage;

import Enums.RegisterState;

public class SocketResponseRegisterMessage extends SocketMessage {
    RegisterState registerState;

    public SocketResponseRegisterMessage(){setOperationType(SocketOperationType.REGISTERRESPONSE);};
    public SocketResponseRegisterMessage(RegisterState registerState){
        this.registerState = registerState;
        setOperationType(SocketOperationType.REGISTERRESPONSE);
    }

    public RegisterState getRegisterState() {
        return registerState;
    }

    public void setRegisterState(RegisterState loginState) {
        this.registerState = loginState;
    }
}
