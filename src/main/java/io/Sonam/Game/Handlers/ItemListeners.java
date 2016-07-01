package io.Sonam.Game.Handlers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.UUID;

public class ItemListeners implements Listener {

    private SkyWars plugin;
    private MainItems items = new MainItems();
    private HashSet<UUID> uuid = new HashSet<UUID>();

    public ItemListeners(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Action a = e.getAction();
        if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            final Player player = e.getPlayer();
            if(e.getPlayer().getItemInHand().isSimilar(items.getKitSelector())) {
                Inventory inv = Bukkit.createInventory(e.getPlayer(), 27, "Kit Selector");
                e.getPlayer().openInventory(inv);
                return;
            }
            if(e.getPlayer().getItemInHand().isSimilar(items.getLeaveGame())) {
                if(uuid.contains(e.getPlayer().getUniqueId())) {
                    uuid.remove(player.getUniqueId());
                    e.getPlayer().sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Teleportation Canceled!");
                    return;
                }
                e.getPlayer().sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Teleporting you back in 2 seconds, right click again to cancel");
                uuid.add(e.getPlayer().getUniqueId());
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                        if(uuid.contains(player.getUniqueId())) {
                            uuid.remove(player.getUniqueId());
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            out.writeUTF("dev1a");
                            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                        } else {
                            return;
                        }
                    }
                }, 40L);
            }
        }
    }

}
