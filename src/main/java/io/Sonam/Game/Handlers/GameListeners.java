package io.Sonam.Game.Handlers;

import io.Sonam.Game.Menu.ItemStacks.KitSelectorItems;
import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameListeners implements Listener {

    MainItems items = new MainItems();

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

    @EventHandler
    public void onHit(EntityDamageEvent e) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'You Died!\', \'color\':\'red\'}"
        ));
        if(e.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) e.getEntity();
            if(player.getHealth() - e.getDamage() < 0.1) {
                e.setCancelled(true);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                SkyWars.getSpectators().add(player.getUniqueId());
                Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " was killed by " + player.getKiller().getName());
                player.setAllowFlight(true);
                player.teleport(player.getKiller().getLocation());
                player.getInventory().clear();
                KitSelectorItems.clearAll(player);
                player.getInventory().setItem(8, items.getLeaveGame());
                player.getInventory().setItem(0, items.getSpectatorMenu());
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 0, false, false));
                return;
            }
            if(SkyWars.getSpectators().contains(player.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) e.getDamager();
            if(SkyWars.getSpectators().contains(player.getUniqueId())) {
                e.setCancelled(true);
            }
            return;
        }
    }


}
