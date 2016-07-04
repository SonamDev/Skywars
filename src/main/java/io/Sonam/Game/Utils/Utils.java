package io.Sonam.Game.Utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
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

}
