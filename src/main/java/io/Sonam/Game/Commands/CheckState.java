package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class
CheckState implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1) {
            Bukkit.broadcastMessage(SkyWars.getPlayers().toString());
            return false;
        }
        Bukkit.broadcastMessage("Current State: " + ChatColor.YELLOW + SkyWars.getGameManager().getGameState().toString());
        return false;
    }
}
