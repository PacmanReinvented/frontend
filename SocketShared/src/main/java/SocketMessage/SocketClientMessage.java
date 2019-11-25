package SocketMessage;

import Enums.MoveDirection;
import Interfaces.enums.InputTypes;

public class SocketClientMessage extends SocketMessage {

    private MoveDirection inputType;

    public SocketClientMessage(MoveDirection inputType) {
        this.inputType = inputType;
        this.setOperationType(SocketOperationType.INPUT);
    }


    public MoveDirection getInputType() {
        return inputType;
    }

}
