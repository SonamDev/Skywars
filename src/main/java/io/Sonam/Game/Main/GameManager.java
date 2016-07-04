package io.Sonam.Game.Main;

import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Threads.Countdown;
import io.Sonam.Game.Threads.SGCountdown;
import io.Sonam.Game.Utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameManager {

    private int maxPlayers;
    private GameState gameState;
    private static Location[] locations;

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

    public void startPreGame() {
        SkyWars.getGameManager().setGameState(GameState.STARTING);
        new Countdown().runTaskTimer(SkyWars.getPlugin(), 0, 20);
    }

    public void startGame() {
        SkyWars.getGameManager().setGameState(GameState.IN_GAME);
    }

    public void endGame() {

    }

    public void startCountdown(boolean forced) {
        new SGCountdown(forced).runTaskTimer(SkyWars.getPlugin(), 0, 20);
    }

    public void testPreInit(boolean forced) {
        if(!forced) {
            return;
        }
        startCountdown(forced);
    }

    static {
        locations[0] = new Location(Bukkit.getWorld("2k"), 325.5, 44, -328, 90, 0);
        locations[1] = new Location(Bukkit.getWorld("2k"), 306.5, 44, -310.5, 180, 0);
    }
}
