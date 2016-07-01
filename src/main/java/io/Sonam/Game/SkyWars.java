package io.Sonam.Game;

import io.Sonam.Game.Commands.CheckState;
import io.Sonam.Game.Handlers.ItemListeners;
import io.Sonam.Game.Handlers.PreInit;
import io.Sonam.Game.Main.GameManager;
import io.Sonam.Game.Utils.GameState;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;
    private static GameManager gameManager;

    public void onEnable() {
        plugin = this;
        gameManager = new GameManager();
        getServer().getPluginManager().registerEvents(new PreInit(this), this);
        getServer().getPluginManager().registerEvents(new ItemListeners(this), this);
        getCommand("cstate").setExecutor(new CheckState());

        gameManager.setGameState(GameState.PRE_GAME);
    }

    public void onDisable() {

    }

    public static SkyWars getPlugin() {
        return plugin;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

}
