package io.Sonam.Game.Main;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.Sonam.Game.Menu.ItemStacks.KitSelectorItems;
import io.Sonam.Game.Menu.Kit;
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
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class GameManager {

    private int maxPlayers;
    private GameState gameState;
    public static String GAME_WORLD = "2k";
    private Location[] locations = {
            new Location(Bukkit.getWorld(GAME_WORLD), 306.5, 44.0, -345.5, 0, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 323.5, 44.0, -328.5, 90, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 306.5, 44.0, -312.5, 180, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 239.5, 44.0, -246.5, 0, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 223.5, 44.0, -229.5, 180, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 207.5, 44.0, -246.5, 90, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 141.5, 44.0, -315.5, 180, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 124.5, 44.0, -330.5, 90, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 141.5, 44.0, -346.5, 180, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 207.5, 44.0, -410.5, 90, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 223.5, 44.0, -427.5, 0, 0),
            new Location(Bukkit.getWorld(GAME_WORLD), 239.5, 44.0, -410.5, 90, 0),
    };
    private Kit kit = new Kit();

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
        SkyWars.getChestFiller().randomizeChests();
        SkyWars.getGameManager().setGameState(GameState.IN_GAME);
        Collections.shuffle(SkyWars.getPlayers());
        int plamount = SkyWars.getPlayers().size();
        for(int i = 0; i < plamount ; i++) {
            Player target = Bukkit.getPlayer(SkyWars.getPlayers().get(i));
            target.teleport(locations[i]);
        }
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setMaxHealth(40.0);
            player.setHealth(40.0);
            player.setGameMode(GameMode.SURVIVAL);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100000, 15, false, false));
            player.getInventory().clear();
            switch (Utils.getSelectedKit(player)) {
                case CHAMPION:
                    kit.giveChampionKit(player, false);
                    break;
                case ARCHER:
                    kit.giveArcherKit(player, false);
                    break;
                case KNIGHT:
                    kit.giveKnightKit(player, false);
                    break;
                case CHEMIST:
                    kit.giveChemistKit(player, false);
                    break;
                case PYRO:
                    kit.givePyroKit(player, false);
                    break;
                case ARMORER:
                    kit.giveArmorerKit(player, false);
                    break;
                case SCOUT:
                    kit.giveScoutKit(player, false);
                    break;
                default:
                    KitSelectorItems.giveDefault(player);
                    break;
            }
        }

    }

    public void endGame() {
        boolean canStop;
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("dev1a");

            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendPluginMessage(SkyWars.getPlugin(), "BungeeCord", out.toByteArray());
            }

            SkyWars.getGameManager().setGameState(GameState.REBOOTING);
        } finally {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getPlugin(), new Runnable() {
                public void run() {
                    Utils.rollback(GAME_WORLD);
                }
            }, 60L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getPlugin(), new Runnable() {
                public void run() {
                    Bukkit.shutdown();
                }
            }, 115L);
        }



    }

    public void startCountdown(boolean forced) {
        new SGCountdown(forced).runTaskTimer(SkyWars.getPlugin(), 0, 20);
    }

    public void testPreInit(boolean forced) {
        if(Bukkit.getOnlinePlayers().size() < 2) {
            Bukkit.broadcastMessage(ChatColor.RED + "Cannot start game: Not enough players!");
            return;
        }
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
