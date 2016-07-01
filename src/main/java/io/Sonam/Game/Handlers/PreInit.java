package io.Sonam.Game.Handlers;

import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PreInit implements Listener {

    private SkyWars plugin;
    private MainItems items = new MainItems();

    public PreInit(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinTest(PlayerJoinEvent e) {
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setItem(0, items.getKitSelector());
        e.getPlayer().getInventory().setItem(8, items.getLeaveGame());
        Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joined!" + ChatColor.GREEN + " [" + Bukkit.getOnlinePlayers().size() + "/" + SkyWars.getGameManager().getMaxPlayers() + "]");
        e.setJoinMessage(null);
    }
}
