package SocketMessage;

import enums.TileType;

public class SocketResponsePositionMessage extends SocketMessage {

    private TileType[][] positions;


    public SocketResponsePositionMessage(TileType[][] positions) {
        this.positions = positions;
        this.setOperationType(SocketOperationType.POSITIONS);
    }

    public SocketResponsePositionMessage() {
        this.setOperationType(SocketOperationType.POSITIONS);
    }

    public TileType[][] getPositions() {
        return positions;
    }

    public void setPositions(TileType[][] positions) {
        this.positions = positions;
    }
}
