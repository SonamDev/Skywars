package io.Sonam.Game;

import io.Sonam.Game.Commands.CheckState;
import io.Sonam.Game.Commands.Restart;
import io.Sonam.Game.Commands.StartGame;
import io.Sonam.Game.Handlers.GameListeners;
import io.Sonam.Game.Handlers.ItemListeners;
import io.Sonam.Game.Handlers.PluginListener;
import io.Sonam.Game.Handlers.PreInit;
import io.Sonam.Game.Main.GameManager;
import io.Sonam.Game.Utils.GameState;
import io.Sonam.Game.Utils.Kits;
import io.Sonam.Game.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;
    private static GameManager gameManager;
    private PluginListener pluginListener;
    private static HashMap<UUID, Kits> kitSelected = new HashMap<UUID, Kits>();
    private static ArrayList<UUID> players = new ArrayList<UUID>();
    public static boolean debug;

    public void onEnable() {
        Utils.loadMap("2k");
        Bukkit.getWorld("2k").setMonsterSpawnLimit(0);
        Bukkit.getWorld("2k").setWaterAnimalSpawnLimit(0);
        Bukkit.getWorld("2k").setAnimalSpawnLimit(0);
        plugin = this;
        pluginListener = new PluginListener();
        debug = true;
        gameManager = new GameManager();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", pluginListener);
        getServer().getPluginManager().registerEvents(new PreInit(this), this);
        getServer().getPluginManager().registerEvents(new ItemListeners(this), this);
        getServer().getPluginManager().registerEvents(new GameListeners(), this);
        getCommand("cstate").setExecutor(new CheckState());
        getCommand("forcestart").setExecutor(new StartGame());
        getCommand("restarts").setExecutor(new Restart());

        gameManager.setGameState(GameState.PRE_GAME);
        gameManager.setMaxPlayers(12);
        if(Bukkit.getOnlinePlayers().size() != 0) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                players.add(player.getUniqueId());
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Server Booted! GameState: PRE_GAME");
    }

    public void onDisable() {
        Utils.unloadMap("2k");
    }

    public static SkyWars getPlugin() {
        return plugin;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static HashMap<UUID, Kits> getKitSelected() {
        return kitSelected;
    }

    public static ArrayList<UUID> getPlayers() {
        return players;
    }
}
