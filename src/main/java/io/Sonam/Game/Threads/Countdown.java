package io.Sonam.Game.Threads;

import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Utils.Utils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    int count = 10;

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (count > -1) {
                if (count == 0) {
                    Bukkit.broadcastMessage(count + "");
                    PacketPlayOutTitle title1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, IChatBaseComponent.ChatSerializer.a(""));
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title1);
                    SkyWars.getGameManager().startGame();
                    cancel();
                }
                Bukkit.broadcastMessage(count + "");
                Utils.sendSubTitle(player, count + "", "red");
                count--;
            }
        }

    }
}