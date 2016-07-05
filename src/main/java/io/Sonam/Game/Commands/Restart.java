package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Restart implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        SkyWars.getGameManager().endGame();

        return false;
    }
}
