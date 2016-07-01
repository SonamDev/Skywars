package io.Sonam.Game.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitSelector {

    public ItemStack champion() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Champion Kit");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack archer() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Archer Kit");
        item.setItemMeta(meta);
        return item;
    }

    public  ItemStack knight() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Knight Kit");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack chemist() {
        ItemStack item = new ItemStack(Material.POTION, 1, (short) 16388);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Chemist Kit");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack pyro() {
        ItemStack item = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Pyro Kit");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack armorer() {
        ItemStack item = new ItemStack(Material.GOLD_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Armorer Kit");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack scout() {
        ItemStack item = new ItemStack(Material.POTION, 1, (short) 8258);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Scout Kit");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        return item;
    }

    public Inventory getSelector() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Kit Selector");
        inventory.setItem(10, champion());
        inventory.setItem(11, archer());
        inventory.setItem(12, knight());
        inventory.setItem(13, chemist());
        inventory.setItem(14, pyro());
        inventory.setItem(15, armorer());
        inventory.setItem(16, scout());
        return inventory;
    }


}
