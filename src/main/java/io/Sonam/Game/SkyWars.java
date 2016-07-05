package io.Sonam.Game;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
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
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;
    private static GameManager gameManager;
    private PluginListener pluginListener;
    private static HashMap<UUID, Kits> kitSelected = new HashMap<UUID, Kits>();
    private static ArrayList<UUID> players = new ArrayList<UUID>();
    private static ArrayList<UUID> spectators = new ArrayList<UUID>();
    public static boolean debug;
    public static boolean gameRunning;
    private static CuboidSelection map;
    private static HashSet<Block> chests = new HashSet<Block>();
    public World world;

    public void onEnable() {
        Utils.loadMap(GameManager.GAME_WORLD);
        Bukkit.getWorld(GameManager.GAME_WORLD).setAutoSave(false);
        Bukkit.getWorld(GameManager.GAME_WORLD).setMonsterSpawnLimit(0);
        Bukkit.getWorld(GameManager.GAME_WORLD).setWaterAnimalSpawnLimit(0);
        Bukkit.getWorld(GameManager.GAME_WORLD).setAnimalSpawnLimit(0);
        plugin = this;
        pluginListener = new PluginListener();
        debug = true;
        gameRunning = true;
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
        world = Bukkit.getWorld(GameManager.GAME_WORLD);
        map = new CuboidSelection(world, new Location(world, 364.5, 18, -225.5), new Location(world, 111.0, 113.0, -449.0));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Server Booted! GameState: PRE_GAME");
    }

    public WorldEditPlugin getWorldEdit() {
        Plugin p = getServer().getPluginManager().getPlugin("WorldEdit");
        if(p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
        else return null;
    }

    public void setChestLocations() {
        CuboidSelection sel = SkyWars.getMap();
        Vector min = sel.getNativeMinimumPoint();
        Vector max = sel.getNativeMaximumPoint();
        for(int x = min.getBlockX();x <= max.getBlockX(); x=x+1){
            for(int y = min.getBlockY();y <= max.getBlockY(); y=y+1){
                for(int z = min.getBlockZ();z <= max.getBlockZ(); z=z+1){
                    Location tmpblock = new Location(world, x, y, z);
                    if(tmpblock.getBlock().getType().equals(Material.CHEST)) {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + tmpblock.toString());
                        chests.add(tmpblock.getBlock());
                    }
                }
            }
        }
    }

    public void onDisable() {

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

    public static ArrayList<UUID> getSpectators() {
        return spectators;
    }

    public static CuboidSelection getMap() {
        return map;
    }
}
