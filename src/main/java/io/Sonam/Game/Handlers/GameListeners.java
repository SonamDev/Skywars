package io.Sonam.Game.Handlers;

import io.Sonam.Core;
import io.Sonam.Game.Events.GamePlayerDeathEvent;
import io.Sonam.Game.Menu.ItemStacks.KitSelectorItems;
import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Stats.GameProfile;
import io.Sonam.Game.Utils.GameState;
import io.Sonam.Game.Utils.Utils;
import io.Sonam.profiler.PlayerProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GameListeners implements Listener {

    MainItems items = new MainItems();

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        switch (e.getBlock().getType()) {
            case IRON_ORE:
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                e.getBlock().setType(Material.AIR);
                break;
            case GOLD_ORE:
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
                e.getBlock().setType(Material.AIR);
                break;
        }
    }

    @EventHandler
    public void onHit(EntityDamageEvent e) {
        Bukkit.broadcastMessage(ChatColor.YELLOW + e.getCause().name());
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'You Died!\', \'color\':\'red\'}"
        ), 13, 60, 10);
        PacketPlayOutTitle resetsubs = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(
                ""
        ));
        if(e.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) e.getEntity();
            if(player.getHealth() - e.getFinalDamage() < 0.1 || e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                SkyWars.getSpectators().add(player.getUniqueId());
                SkyWars.getPlugin().getServer().getPluginManager().callEvent(new GamePlayerDeathEvent(player));
                e.setCancelled(true);
                player.setMaxHealth(20.0);
                player.setHealth(20.0);
                player.setFoodLevel(20);
                player.setAllowFlight(true);
                player.setFlying(true);
                player.damage(0.1);
                for(UUID uuid : SkyWars.getSpectators()) {
                    player.showPlayer(Bukkit.getPlayer(uuid));
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 0, false, false));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(resetsubs);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                switch (e.getCause()) {
                    case FALL:
                        if(((Player) e.getEntity()).getKiller() != null) {
                            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " was knocked off a cliff by " + ChatColor.RED + player.getKiller().getName());
                            break;
                        }
                        Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " fell off a cliff.");
                        break;
                    case VOID:
                        if(((Player) e.getEntity()).getKiller() != null) {
                            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " was knocked into the void by " + ChatColor.RED + player.getKiller().getName());
                            break;
                        }
                        break;
                    default:
                        if(((Player) e.getEntity()).getKiller() != null) {
                            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " was killed by " + ChatColor.RED + player.getKiller().getName());
                            break;
                        }
                        break;
                }
                Location[] locations = SkyWars.getGameManager().getLocations();
                Location loc = locations[SkyWars.getPlayers().indexOf(player.getUniqueId())];
                loc.setY(loc.getY() + 8.4);
                player.teleport(loc);
                player.getInventory().clear();
                KitSelectorItems.clearAll(player);
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(!SkyWars.getSpectators().contains(p.getUniqueId())) {
                        p.hidePlayer(player);
                    } else {
                        p.showPlayer(player);
                    }
                }
                player.getInventory().setItem(8, items.getLeaveGame());
                player.getInventory().setItem(0, items.getSpectatorMenu());

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
        Player killedPlayer = e.getPlayer();
        if(killedPlayer.getKiller() != null) {
            SkyWars.getGameProfileManager().getGameProfile(killedPlayer.getKiller().getUniqueId()).addCoins(20);
            killedPlayer.getKiller().playSound(killedPlayer.getKiller().getLocation(), Sound.NOTE_PLING, 1, 5);
            killedPlayer.getKiller().sendMessage(ChatColor.GOLD + "+20 coins!");
        }
        Scoreboard board = SkyWars.scoreboards.get(e.getPlayer().getUniqueId());
        board.createTeam("spec");
        ScoreboardTeam spec = board.getTeam("spec");
        board.getTeam(e.getPlayer().getName()).getPlayerNameSet().remove(e.getPlayer().getName());
        ArrayList<String> test = new ArrayList<String>();
        for(UUID uuid : SkyWars.getSpectators()) {
            Player player = Bukkit.getPlayer(uuid);
            test.add(player.getName());
            if(!uuid.equals(e.getPlayer().getUniqueId())) {
                ScoreboardTeam specc = SkyWars.scoreboards.get(uuid).getTeam("spec");
                specc.getPlayerNameSet().add(e.getPlayer().getName());
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(specc, 1));
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(specc, 0));
            }
        }
        spec.getPlayerNameSet().addAll(test);
        spec.setPrefix(ChatColor.GRAY.toString());
        ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(spec, 0));
        if((SkyWars.getSpectators().size() + 1) == Bukkit.getOnlinePlayers().size()) {
            String winner;
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(!SkyWars.getSpectators().contains(player.getUniqueId())) {
                    winner = player.getName();
                    UUID uuid = Bukkit.getPlayer(winner).getUniqueId();
                    SkyWars.getGameProfileManager().getGameProfile(uuid).addCoins(500);
                    Bukkit.getPlayer(uuid).sendMessage(ChatColor.GOLD + "+500 coins!");
                    PlayerProfile profile = Core.getProfileManager().getProfile(player.getUniqueId());
                    player.sendMessage(ChatColor.GREEN.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
                    player.sendMessage("");
                    Utils.sendCenteredMessage(player, "§e§lWINNER");
                    Utils.sendCenteredMessage(player, ChatColor.translateAlternateColorCodes('&', "          " + profile.getPrefix() + " " + winner));
                    player.sendMessage("");
                    player.sendMessage(ChatColor.GREEN.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
                    SkyWars.getGameManager().setGameState(GameState.REBOOTING);
                    SkyWars.gameRunning = false;
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getPlugin(), new Runnable() {
                public void run() {
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        GameProfile profile = SkyWars.getGameProfileManager().getGameProfile(player.getUniqueId());
                        player.sendMessage(ChatColor.GREEN.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
                        Utils.sendCenteredMessage(player, "§lSummary\n" + "\n");
                        Utils.sendCenteredMessage(player, "§6You got " + profile.getCoins() + " coins.\n\n");
                        player.sendMessage(ChatColor.GREEN.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
                    }
                }
            }, 40L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getPlugin(), new Runnable() {
                public void run() {
                    SkyWars.getGameManager().endGame();
                }
            }, 160L);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!SkyWars.gameRunning || SkyWars.getGameManager().getGameState().equals(GameState.PRE_GAME) || SkyWars.getGameManager().getGameState().equals(GameState.STARTING)) {
            e.setCancelled(true);
        }
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
