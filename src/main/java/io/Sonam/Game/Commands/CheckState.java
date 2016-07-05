package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class
CheckState implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("toggle")) {
                if(!SkyWars.debug) {
                    SkyWars.debug = true;
                } else {
                    SkyWars.debug = false;
                }
            }
        }
        Bukkit.broadcastMessage("Current State: " + ChatColor.YELLOW + SkyWars.getGameManager().getGameState().toString());
        return false;
    }
}
