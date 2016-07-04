package io.Sonam.Game.Handlers;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class GameListeners implements Listener {

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        switch (e.getBlock().getType()) {
            case IRON_ORE:
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                e.getBlock().setType(Material.AIR);
                break;
            case GOLD_ORE:
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                e.getBlock().setType(Material.AIR);
                break;
        }
    }

}
