package io.Sonam.Game.Main;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.Sonam.Game.Menu.ItemStacks.KitSelectorItems;
import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Threads.Countdown;
import io.Sonam.Game.Threads.SGCountdown;
import io.Sonam.Game.Utils.GameState;
import io.Sonam.Game.Utils.Kits;
import io.Sonam.Game.Utils.Utils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class GameManager {

    private int maxPlayers;
    private GameState gameState;
    private Location[] locations = {
            new Location(Bukkit.getWorld("2k"), 306.5, 44.0, -345.5, 0, 0),
            new Location(Bukkit.getWorld("2k"), 323.5, 44.0, -328.5, 90, 0),
            new Location(Bukkit.getWorld("2k"), 306.5, 44.0, -312.5, 180, 0),
            new Location(Bukkit.getWorld("2k"), 239.5, 44.0, -246.5, 0, 0),
            new Location(Bukkit.getWorld("2k"), 223.5, 44.0, -229.5, 180, 0),
            new Location(Bukkit.getWorld("2k"), 207.5, 44.0, -246.5, 90, 0),
            new Location(Bukkit.getWorld("2k"), 141.5, 44.0, -315.5, 180, 0),
            new Location(Bukkit.getWorld("2k"), 124.5, 44.0, -330.5, 90, 0),
            new Location(Bukkit.getWorld("2k"), 141.5, 44.0, -346.5, 180, 0),
            new Location(Bukkit.getWorld("2k"), 207.5, 44.0, -410.5, 90, 0),
            new Location(Bukkit.getWorld("2k"), 223.5, 44.0, -427.5, 0, 0),
            new Location(Bukkit.getWorld("2k"), 239.5, 44.0, -410.5, 90, 0),
    };

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void startPreGame() {
        SkyWars.getGameManager().setGameState(GameState.STARTING);
        for(Player player : Bukkit.getOnlinePlayers()) {
            Kits kit = SkyWars.getKitSelected().get(player.getUniqueId());
            Bukkit.broadcastMessage(kit.name());
            PacketPlayOutTitle times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, IChatBaseComponent.ChatSerializer.a(""), 0, 1000000, 0);
            final PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(
                    "{\'text\':\'Game starts in\', \'color\':\'yellow\'}"
            ));
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        }
        new Countdown().runTaskTimer(SkyWars.getPlugin(), 0, 20);
    }

    public void startGame() {
        SkyWars.getGameManager().setGameState(GameState.IN_GAME);
        Collections.shuffle(SkyWars.getPlayers());
        int plamount = SkyWars.getPlayers().size();
        for(int i = 0; i < plamount ; i++) {
            Player target = Bukkit.getPlayer(SkyWars.getPlayers().get(i));
            target.teleport(locations[i]);
            KitSelectorItems.giveDefault(target);
        }
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setMaxHealth(40.0);
            player.setHealth(40.0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100000, 3, false, false));
        }
    }

    public void endGame() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("dev1a");

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.sendPluginMessage(SkyWars.getPlugin(), "BungeeCord", out.toByteArray());
        }

        while(Bukkit.getOnlinePlayers().size() != 0) {
            return;
        }

        Utils.unloadMap("2k");

        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.shutdown();
    }

    public void startCountdown(boolean forced) {
        new SGCountdown(forced).runTaskTimer(SkyWars.getPlugin(), 0, 20);
    }

    public void testPreInit(boolean forced) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "20 seconds until start!");
        if(!forced) {
            return;
        }
        startCountdown(forced);
    }

    public Location[] getLocations() {
        return locations;
    }

}
