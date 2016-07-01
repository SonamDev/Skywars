package io.Sonam.Game;

import io.Sonam.Game.Handlers.PreInit;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;

    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new PreInit(this), this);
    }

    public void onDisable() {

    }

    public static SkyWars getPlugin() {
        return plugin;
    }


}
