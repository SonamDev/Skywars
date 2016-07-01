package io.Sonam.Game.Commands;

import io.Sonam.Game.SkyWars;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers;
import org.bukkit.entity.Player;

public class CheckState implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Bukkit.broadcastMessage("Current State: " + ChatColor.YELLOW + SkyWars.getGameManager().getGameState().toString());
        Player player = (Player) sender;
        Location test = player.getLocation();
        CraftPlayer craftPlayer = (CraftPlayer) player;
        CraftWorld world = (CraftWorld) player.getWorld();
        PacketPlayOutBlockChange block = new PacketPlayOutBlockChange(world.getHandle(), new BlockPosition(test.getX(), test.getY(), test.getZ()));
        block.block = CraftMagicNumbers.getBlock(Material.DIAMOND_BLOCK).fromLegacyData(0);

        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\'text\':\'Welcome To\', \'color\':\'red\'}"));
        PacketPlayOutTitle packet2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\'text\':\'SkyWars UHC Mode\', \'color\':\'aqua\'}"));
        craftPlayer.getHandle().playerConnection.sendPacket(packet);
        craftPlayer.getHandle().playerConnection.sendPacket(packet2);
        craftPlayer.getHandle().playerConnection.sendPacket(block);

        return false;
    }
}
