package SocketMessage;

import enums.GameState;

public class SocketResponseGameStateMessage extends SocketMessage{

    private GameState gameState;


    public SocketResponseGameStateMessage(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
