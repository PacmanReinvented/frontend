package SocketMessage;

import Interfaces.enums.MoveDirection;

public class SocketClientInputMessage extends SocketMessage {

    private MoveDirection inputType;

    public SocketClientInputMessage(MoveDirection inputType) {
        this.inputType = inputType;
    }


    public MoveDirection getInputType() {
        return inputType;
    }

}
