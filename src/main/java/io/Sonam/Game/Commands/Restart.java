package io.Sonam.Game.Commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Restart implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("dev1a");

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.sendPluginMessage(SkyWars.getPlugin(), "BungeeCord", out.toByteArray());
        }

        Utils.rollback("2k");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.shutdown();

        return false;
    }
}
