package SocketMessage;

public class SocketMessage {

    //Because JSon inputs can always seemingly be parsed into any class using GSON, we use this class
    //to define what type of message we have, then we can parse it into its actual class.
    //I'd prefer it not to exist, but it's seemingly needed...

    private SocketOperationType operationType;

    public SocketOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(SocketOperationType operationType) {
        this.operationType = operationType;
    }


}
