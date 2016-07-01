package io.Sonam.Game.Main;

import io.Sonam.Game.Utils.GameState;

public class GameManager {

    private int maxPlayers;
    private GameState gameState;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}