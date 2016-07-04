package io.Sonam.Game.Threads;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SGCountdown extends BukkitRunnable {

    public boolean forced;
    int count = 10;

    public SGCountdown(boolean forced) {
        this.forced = forced;
    }

    public void run() {
        if(Bukkit.getOnlinePlayers().size() < 10) {
            if(!forced) {
                Bukkit.broadcastMessage(ChatColor.RED + "Not enough players to start!");
                cancel();
            }
        }
        if(count > 0) {
            count--;
        } else if(count == 0) {
            SkyWars.getGameManager().startPreGame();
            cancel();
        }
    }

}
