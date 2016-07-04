package io.Sonam.Game.Main;

import io.Sonam.Game.Menu.ItemStacks.KitSelectorItems;
import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Threads.Countdown;
import io.Sonam.Game.Threads.SGCountdown;
import io.Sonam.Game.Utils.GameState;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class GameManager {

    private int maxPlayers;
    private GameState gameState;
    private static Location[] locations = {
            new Location(Bukkit.getWorld("2k"), 325.5, 44.0, -328.5, 90, 0),
            new Location(Bukkit.getWorld("2k"), 306.5, 44.0, -310.5, 180, 0),
            new Location(Bukkit.getWorld("2k"), 306.5, 44.0, -346.5, 0, 0),
            new Location(Bukkit.getWorld("2k"), 325.5, 44.0, -328.5, 90, 0),
            new Location(Bukkit.getWorld("2k"), 325.5, 44.0, -328.5, 90, 0),
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
        int plamount = SkyWars.getPlayers().size();
        for(int i = 0; i < plamount ; i++) {
            Player target = Bukkit.getPlayer(SkyWars.getPlayers().get(i));
            target.teleport(locations[i]);
            KitSelectorItems.giveDefault(target);
        }
    }

    public void endGame() {

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

    public static Location[] getLocations() {
        return locations;
    }

}
