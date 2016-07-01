package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckState implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Bukkit.broadcastMessage("Current State: " + ChatColor.YELLOW + SkyWars.getGameManager().getGameState().toString());

        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(SkyWars.getKitSelected().containsKey(target.getUniqueId())) {
                sender.sendMessage(ChatColor.GREEN + "That player is using the " + SkyWars.getKitSelected().get(target.getUniqueId()) + " kit.");
                return false;
            }
            sender.sendMessage(ChatColor.GREEN + "That player is using the Default kit.");
        }

        return false;
    }
}
