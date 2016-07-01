package io.Sonam.Game.Handlers;

import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PreInit implements Listener {

    private SkyWars plugin;
    private MainItems items = new MainItems();

    public PreInit(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinTest(PlayerJoinEvent e) {
        e.getPlayer().setHealth(20.0);
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().teleport(new Location(Bukkit.getWorld("Game"), 8.5, 12, 32.5, 180F, 0F));
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setItem(0, items.getKitSelector());
        e.getPlayer().getInventory().setItem(8, items.getLeaveGame());
        Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joined!" + ChatColor.GREEN + " [" + Bukkit.getOnlinePlayers().size() + "/" + SkyWars.getGameManager().getMaxPlayers() + "]");
        e.setJoinMessage(null);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity().getType().equals(EntityType.PLAYER)) {
            if(SkyWars.getGameManager().getGameState().equals(GameState.PRE_GAME)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if(e.getEntity().getType().equals(EntityType.PLAYER)) {
            if(SkyWars.getGameManager().getGameState().equals(GameState.PRE_GAME)) {
                e.setCancelled(true);
            }
        }
    }
}
