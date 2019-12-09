package SocketMessage;

import enums.TileType;

public class SocketResponsePositionMessage extends SocketMessage {

    private TileType[][] positions;


    public SocketResponsePositionMessage(TileType[][] positions) {
        this.positions = positions;
    }

    public TileType[][] getPositions() {
        return positions;
    }
}
