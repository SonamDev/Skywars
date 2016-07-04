package io.Sonam.Game;

import io.Sonam.Game.Commands.CheckState;
import io.Sonam.Game.Commands.Restart;
import io.Sonam.Game.Commands.StartGame;
import io.Sonam.Game.Handlers.ItemListeners;
import io.Sonam.Game.Handlers.PluginListener;
import io.Sonam.Game.Handlers.PreInit;
import io.Sonam.Game.Main.GameManager;
import io.Sonam.Game.Utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;
    private static GameManager gameManager;
    private PluginListener pluginListener;
    private static HashMap<UUID, String> kitSelected = new HashMap<UUID, String>();
    private static ArrayList<UUID> players = new ArrayList<UUID>();
    public static boolean debug;

    public void onEnable() {
        plugin = this;
        pluginListener = new PluginListener();
        debug = true;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", pluginListener);
        gameManager = new GameManager();
        getServer().getPluginManager().registerEvents(new PreInit(this), this);
        getServer().getPluginManager().registerEvents(new ItemListeners(this), this);
        getCommand("cstate").setExecutor(new CheckState());
        getCommand("forcestart").setExecutor(new StartGame());
        getCommand("restarts").setExecutor(new Restart());

        if(Bukkit.getServer().unloadWorld("2k",false))
        {
            new File("dev1b/2k").delete();
            Bukkit.broadcastMessage(ChatColor.GREEN + "World Rolledback...");
            new WorldCreator("2k").createWorld();
        }

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
        Bukkit.getWorld("2k").setAutoSave(false);
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

    public static ArrayList<UUID> getPlayers() {
        return players;
    }
}
