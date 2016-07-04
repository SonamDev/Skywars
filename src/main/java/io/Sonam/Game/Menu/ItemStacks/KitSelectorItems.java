package io.Sonam.Game.Menu.ItemStacks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class KitSelectorItems {

    public static void giveDefault(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.clear();
        inv.setHelmet(new ItemStack(Material.LEATHER_HELMET));
        inv.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        inv.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        inv.setItem(0, new ItemStack(Material.STONE_SWORD));
        inv.setItem(1, new ItemStack(Material.STONE_AXE));
        inv.setItem(2, new ItemStack(Material.IRON_PICKAXE));
        inv.setItem(3, new ItemStack(Material.STONE_SPADE));
    }

}
