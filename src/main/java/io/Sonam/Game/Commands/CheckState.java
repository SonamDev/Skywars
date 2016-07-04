package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import io.Sonam.Game.Threads.Countdown;
import io.Sonam.Game.Utils.Utils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class
CheckState implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("test")) {
                final PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(
                        "{\'text\':\'Game starts in\', \'color\':\'yellow\'}"
                ), 0, 1000000, 0);
                final Player player = (Player) sender;
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);

                BukkitTask task = new Countdown().runTaskTimer(SkyWars.getPlugin(), 0, 20);

                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(SkyWars.getKitSelected().containsKey(target.getUniqueId())) {
                sender.sendMessage(ChatColor.GREEN + "That player is using the " + SkyWars.getKitSelected().get(target.getUniqueId()) + " kit.");
                return false;
            }
            sender.sendMessage(ChatColor.GREEN + "That player is using the Default kit.");
            return false;
        }
        Bukkit.broadcastMessage("Current State: " + ChatColor.YELLOW + SkyWars.getGameManager().getGameState().toString());
        return false;
    }
}
