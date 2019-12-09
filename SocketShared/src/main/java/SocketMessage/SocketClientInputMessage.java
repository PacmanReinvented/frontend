package SocketMessage;

import enums.MoveDirection;

public class SocketClientInputMessage extends SocketMessage {

    private MoveDirection inputType;

    public SocketClientInputMessage(MoveDirection inputType) {
        this.setOperationType(SocketOperationType.INPUT);
        this.inputType = inputType;
    }

    public SocketClientInputMessage() {
        this.setOperationType(SocketOperationType.INPUT);
    }


    public MoveDirection getInputType() {
        return inputType;
    }

    public void setInputType(MoveDirection inputType) {
        this.inputType = inputType;
    }
}
