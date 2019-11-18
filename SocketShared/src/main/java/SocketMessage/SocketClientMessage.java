package SocketMessage;

import Interfaces.enums.InputTypes;

public class SocketClientMessage extends SocketMessage {

    private InputTypes inputType;

    public SocketClientMessage(InputTypes inputType) {
        this.inputType = inputType;
    }


    public InputTypes getInputType() {
        return inputType;
    }

}
