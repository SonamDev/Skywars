package io.Sonam.Game.Handlers;

import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class ItemListeners implements Listener {

    private SkyWars plugin;
    private MainItems items = new MainItems();

    public ItemListeners(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Action a = e.getAction();
        if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getPlayer().getItemInHand().isSimilar(items.getKitSelector())) {
                Inventory inv = Bukkit.createInventory(e.getPlayer(), 27, "Kit Selector");
                e.getPlayer().openInventory(inv);
            }
        }
    }

}
