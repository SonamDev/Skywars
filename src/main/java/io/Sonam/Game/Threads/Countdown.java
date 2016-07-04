package io.Sonam.Game.Threads;

import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    int count = 10;

    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(count > 0) {
                count--;
                if(SkyWars.debug) Bukkit.broadcastMessage(count + "");
                Utils.sendSubTitle(player, count + "", "red");
            } else if(count == 0) {
                if(SkyWars.debug) Bukkit.broadcastMessage(count + "");
                Utils.clearTitle(player);
                SkyWars.getGameManager().startGame();
                cancel();
            }
        }



    }
}