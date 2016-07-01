package io.Sonam.Game;

import io.Sonam.Game.Commands.CheckState;
import io.Sonam.Game.Handlers.ItemListeners;
import io.Sonam.Game.Handlers.PluginListener;
import io.Sonam.Game.Handlers.PreInit;
import io.Sonam.Game.Main.GameManager;
import io.Sonam.Game.Utils.GameState;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;
    private static GameManager gameManager;
    private PluginListener pluginListener;
    private static HashMap<UUID, String> kitSelected = new HashMap<UUID, String>();

    public void onEnable() {
        plugin = this;
        pluginListener = new PluginListener();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", pluginListener);
        gameManager = new GameManager();
        getServer().getPluginManager().registerEvents(new PreInit(this), this);
        getServer().getPluginManager().registerEvents(new ItemListeners(this), this);
        getCommand("cstate").setExecutor(new CheckState());

        gameManager.setGameState(GameState.PRE_GAME);
        gameManager.setMaxPlayers(12);
    }

    public void onDisable() {

    }

    public static SkyWars getPlugin() {
        return plugin;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static HashMap<UUID, String> getKitSelected() {
        return kitSelected;
    }
}
