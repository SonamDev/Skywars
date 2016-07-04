package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartGame implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(SkyWars.debug) Bukkit.broadcastMessage(ChatColor.RED + "[ADMIN] " + sender.getName() + ChatColor.YELLOW +  " force started the game.");

        SkyWars.getGameManager().testPreInit(true);
        return false;
    }
}
