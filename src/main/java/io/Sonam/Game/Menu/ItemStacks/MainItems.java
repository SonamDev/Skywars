package io.Sonam.Game.Menu.ItemStacks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainItems {

    private ItemStack kitSelector() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Kit Selector " + ChatColor.GRAY + "(Right Click)");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right click to access the");
        lore.add(ChatColor.GRAY + "kit menu!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack leaveGame() {
        ItemStack item = new ItemStack(Material.BED);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Leave The Game " + ChatColor.GRAY + "(Right Click)");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right click to go back to the lobby!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack spectatorMenu() {
        ItemStack item = new ItemStack(Material.BED);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Spectator Menu " + ChatColor.GRAY + "(Right Click)");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right click to open the Spectator Menu!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getKitSelector() {
        return kitSelector();
    }

    public ItemStack getLeaveGame() {
        return leaveGame();
    }

    public ItemStack getSpectatorMenu() {
        return spectatorMenu();
    }

}
