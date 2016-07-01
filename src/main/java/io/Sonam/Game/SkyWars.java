package io.Sonam.Game;

import org.bukkit.plugin.java.JavaPlugin;

public class SkyWars extends JavaPlugin {

    private static SkyWars plugin;

    public void onEnable() {
        plugin = this;
    }

    public void onDisable() {

    }

    public static SkyWars getPlugin() {
        return plugin;
    }


}
