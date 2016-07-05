package io.Sonam.Game.Utils;

import io.Sonam.Game.SkyWars;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {

    public static void sendTitle(Player player, String title, String subtitle, String color1, String color2) {
        PacketPlayOutTitle titl = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'"+ title + "\', \'color\':\'" + color1 + "\'}"
        ));
        PacketPlayOutTitle subtitl = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'"+ subtitle + "\', \'color\':\'" + color2 + "\'}"
        ));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titl);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitl);
    }

    public static void sendSubTitle(Player player, String subtitle, String color1) {
        PacketPlayOutTitle subtitl = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a(
                "{\'text\':\'"+ subtitle + "\', \'color\':\'" + color1 + "\'}"
        ), 0, 100000, 0);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitl);
    }

    public static void clearTitle(Player player) {
        PacketPlayOutTitle clear = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, IChatBaseComponent.ChatSerializer.a(
                ""
        ));
        PacketPlayOutTitle reset = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, IChatBaseComponent.ChatSerializer.a(
                ""
        ));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(clear);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(reset);
    }

    //Unloading maps, to rollback maps. Will delete all player builds until last server save
    public static void unloadMap(String mapname){
        if(Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(mapname), false)){
            SkyWars.getPlugin().getLogger().info("Successfully unloaded " + mapname);
        }else{
            SkyWars.getPlugin().getLogger().severe("COULD NOT UNLOAD " + mapname);
        }
    }
    //Loading maps (MUST BE CALLED AFTER UNLOAD MAPS TO FINISH THE ROLLBACK PROCESS)
    public static void loadMap(String mapname){
        Bukkit.getServer().createWorld(new WorldCreator(mapname));
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Map Loaded.");
    }

    //Maprollback method, because were too lazy to type 2 lines
    public static void rollback(final String mapname){
        unloadMap(mapname);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getPlugin(), new Runnable() {
            public void run() {
                loadMap(mapname);
            }
        }, 35L);

    }

}
