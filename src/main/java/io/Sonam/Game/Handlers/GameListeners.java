package io.Sonam.Game.Handlers;

import io.Sonam.Game.Events.GamePlayerDeathEvent;
import io.Sonam.Game.Menu.ItemStacks.KitSelectorItems;
import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

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
        ), 13, 60, 10);
        PacketPlayOutTitle resetsubs = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(
                ""
        ));
        if(e.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) e.getEntity();
            if(player.getHealth() - e.getDamage() < 0.1) {
                SkyWars.getSpectators().add(player.getUniqueId());
                e.setCancelled(true);
                player.setAllowFlight(true);
                player.setFlying(true);
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(!SkyWars.getSpectators().contains(p.getUniqueId())) {
                        p.hidePlayer(player);
                    } else {
                        p.showPlayer(player);
                    }
                }
                for(UUID uuid : SkyWars.getSpectators()) {
                    player.showPlayer(Bukkit.getPlayer(uuid));
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 0, false, false));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(resetsubs);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " was killed by " + ChatColor.RED + player.getKiller().getName());
                SkyWars.getPlugin().getServer().getPluginManager().callEvent(new GamePlayerDeathEvent(player));
                Location[] locations = SkyWars.getGameManager().getLocations();
                Location loc = locations[SkyWars.getPlayers().indexOf(player.getUniqueId())];
                loc.setY(loc.getY() + 8.4);
                player.teleport(loc);
                player.getInventory().clear();
                KitSelectorItems.clearAll(player);
                player.getInventory().setItem(8, items.getLeaveGame());
                player.getInventory().setItem(0, items.getSpectatorMenu());
                player.setHealth(20.0);
                player.setFoodLevel(20);
                return;
            }
            if(SkyWars.getSpectators().contains(player.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(GamePlayerDeathEvent e) {
        Bukkit.broadcastMessage(ChatColor.RED + "DEBUG Event Called > " + SkyWars.getSpectators().size() + " >> " + Bukkit.getOnlinePlayers().size());
        if((SkyWars.getSpectators().size() + 1) == Bukkit.getOnlinePlayers().size()) {
            String winner;
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(!SkyWars.getSpectators().contains(player.getUniqueId())) {
                    winner = player.getName();
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + winner + " won the game!");
                    Bukkit.broadcastMessage("");
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getPlugin(), new Runnable() {
                public void run() {
                    SkyWars.getGameManager().endGame();
                }
            }, 120L);
        }
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

    @EventHandler
    public void onRegen(EntityRegainHealthEvent e) {
        if(e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)
            e.setCancelled(true);
    }


}
