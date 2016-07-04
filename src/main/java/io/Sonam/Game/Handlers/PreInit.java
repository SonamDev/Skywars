package io.Sonam.Game.Handlers;

import io.Sonam.Game.Menu.ItemStacks.MainItems;
import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Utils.GameState;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PreInit implements Listener {

    private SkyWars plugin;
    private MainItems items = new MainItems();

    public PreInit(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        if(SkyWars.getGameManager().getGameState().equals(GameState.STARTING) || SkyWars.getGameManager().getGameState().equals(GameState.IN_GAME)) {
            e.setKickMessage("Game is starting!");
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        SkyWars.getPlayers().add(e.getPlayer().getUniqueId());
        Bukkit.broadcastMessage(SkyWars.getPlayers().toString() + SkyWars.getPlayers().size());
        e.getPlayer().teleport(new Location(Bukkit.getWorld("2k"), 397.5, 8.0, -349.5, 0F, 0F));
        e.getPlayer().setHealth(20.0);
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setItem(0, items.getKitSelector());
        e.getPlayer().getInventory().setItem(8, items.getLeaveGame());
        Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joined. " + ChatColor.GREEN + " [" + Bukkit.getOnlinePlayers().size() + "/" + SkyWars.getGameManager().getMaxPlayers() + "]");
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'Welcome To\', \'color\':\'yellow\'}"
        ));
        PacketPlayOutTitle stitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'Skywars UHC Mode\', \'color\':\'red\'}"
        ));

        ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(stitle);

        if(Bukkit.getOnlinePlayers().size() > 9) {
            SkyWars.getGameManager().testPreInit(false);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        SkyWars.getPlayers().remove(e.getPlayer().getUniqueId());
        switch (SkyWars.getGameManager().getGameState()) {
            case PRE_GAME:
                Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + " left. " + ChatColor.GREEN + " [" + Bukkit.getOnlinePlayers().size() + "/" + SkyWars.getGameManager().getMaxPlayers() + "]");
                break;
            case STARTING:
                Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + " logged out. ");
                break;
            case IN_GAME:
                Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + " logged out. ");
                break;
        }


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
