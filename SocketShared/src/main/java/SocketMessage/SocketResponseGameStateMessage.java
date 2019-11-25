package SocketMessage;

import Enums.GameState;

public class SocketResponseGameStateMessage extends SocketMessage{

    private GameState gameState;


    public SocketResponseGameStateMessage(GameState gameState) {
        this.gameState = gameState;
        this.setOperationType(SocketOperationType.GAMESTATE);
    }

    public GameState getGameState() {
        return gameState;
    }
}
