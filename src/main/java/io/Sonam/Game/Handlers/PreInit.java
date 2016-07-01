package io.Sonam.Game.Handlers;

import io.Sonam.Game.SkyWars;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PreInit implements Listener {

    private SkyWars plugin;

    public PreInit(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinTest(PlayerJoinEvent e) {

    }
}
