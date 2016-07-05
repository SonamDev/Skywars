package io.Sonam.Game.Commands;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class
CheckState implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length == 1) {
            CuboidSelection sel = SkyWars.getMap();
            Vector min = sel.getNativeMinimumPoint();
            Vector max = sel.getNativeMaximumPoint();
            for(int x = min.getBlockX();x <= max.getBlockX(); x=x+1){
                for(int y = min.getBlockY();y <= max.getBlockY(); y=y+1){
                    for(int z = min.getBlockZ();z <= max.getBlockZ(); z=z+1){
                        Location tmpblock = new Location(player.getWorld(), x, y, z);
                        if(tmpblock.getBlock().getType().equals(Material.CHEST)) {
                            Bukkit.broadcastMessage(ChatColor.YELLOW + tmpblock.toString());
                        }
                    }
                }
            }
            return false;
        }
        Bukkit.broadcastMessage("Current State: " + ChatColor.YELLOW + SkyWars.getGameManager().getGameState().toString());
        return false;
    }
}
