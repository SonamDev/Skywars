package io.Sonam.Game.Menu;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class Kit {

    public void giveChampionKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.STONE_SWORD);
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            ItemStack diamond = new ItemStack(Material.DIAMOND, 2);
            inv.addItem(sword, diamond);

            inv.setHelmet(new ItemStack(Material.IRON_HELMET));
            inv.setBoots(new ItemStack(Material.IRON_BOOTS));
            inv.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
            return;
        }

        PlayerInventory inv = player.getInventory();
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        inv.addItem(sword, diamond);

        inv.setHelmet(new ItemStack(Material.IRON_HELMET));
        inv.setBoots(new ItemStack(Material.IRON_BOOTS));
        inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
    }

    public void giveArcherKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.BOW);
            ItemStack arrow = new ItemStack(Material.ARROW, 16);
            sword.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
            inv.addItem(sword, arrow);

            inv.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            inv.setBoots(new ItemStack(Material.IRON_BOOTS));
            inv.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
            return;
        }
        PlayerInventory inv = player.getInventory();

        ItemStack sword = new ItemStack(Material.BOW);
        ItemStack arrow = new ItemStack(Material.ARROW, 16);
        inv.addItem(sword, arrow);

        inv.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        inv.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        inv.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
    }

    public void giveKnightKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.IRON_SWORD);
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
            meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, false);
            book.setItemMeta(meta);
            inv.addItem(sword, book);

            inv.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            inv.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
            return;
        }

        PlayerInventory inv = player.getInventory();
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        inv.addItem(sword);

        inv.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
    }

    public void giveChemistKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.STONE_SWORD);
            sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            ItemStack p1 = new ItemStack(Material.POTION, 1, (short) 16388);
            ItemStack p2 = new ItemStack(Material.POTION, 1, (short) 16387);
            ItemStack p3 = new ItemStack(Material.POTION, 1, (short) 16392);
            ItemStack p4 = new ItemStack(Material.POTION, 1, (short) 8193);
            ItemStack p5 = new ItemStack(Material.POTION, 2, (short) 16418);

            ItemStack pants = new ItemStack(Material.IRON_LEGGINGS);
            pants.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

            inv.addItem(sword, p1, p2, p3, p4, p5);
            inv.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            inv.setLeggings(pants);
            return;
        }
        PlayerInventory inv = player.getInventory();
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemStack p1 = new ItemStack(Material.POTION, 1, (short) 16388);
        ItemStack p2 = new ItemStack(Material.POTION, 1, (short) 16387);
        ItemStack p3 = new ItemStack(Material.POTION, 1, (short) 16392);
        ItemStack p4 = new ItemStack(Material.POTION, 1, (short) 8193);

        inv.addItem(sword, p1, p2, p3, p4);
        inv.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
    }

    public void givePyroKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.STONE_SWORD);
            sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
            ItemStack fns = new ItemStack(Material.FLINT_AND_STEEL);
            ItemStack lavabucket = new ItemStack(Material.LAVA_BUCKET);
            ItemStack fire_res = new ItemStack(Material.POTION, 1, (short) 6387);

            inv.addItem(sword, fns, lavabucket, fire_res);

            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_FIRE, 3);
            inv.setChestplate(chest);
            inv.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            return;
        }
        PlayerInventory inv = player.getInventory();
        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
        ItemStack fns = new ItemStack(Material.FLINT_AND_STEEL);
        ItemStack lavabucket = new ItemStack(Material.LAVA_BUCKET);
        ItemStack fire_res = new ItemStack(Material.POTION, 1, (short) 6387);

        inv.addItem(sword, fns, lavabucket, fire_res);

        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        chest.addEnchantment(Enchantment.PROTECTION_FIRE, 3);
        inv.setChestplate(chest);
    }

    public void giveArmorerKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            inv.setHelmet(new ItemStack(Material.IRON_HELMET));
            inv.setBoots(new ItemStack(Material.GOLD_BOOTS));
            inv.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
            inv.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
            inv.setItem(0, new ItemStack(Material.STONE_SWORD));
            return;
        }
        PlayerInventory inv = player.getInventory();
        inv.setHelmet(new ItemStack(Material.LEATHER_HELMET));
        inv.setBoots(new ItemStack(Material.GOLD_BOOTS));
        inv.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));

    }

    public void giveScoutKit(Player player, boolean prestige) {
        if(prestige) {
            PlayerInventory inv = player.getInventory();
            ItemStack sword = new ItemStack(Material.IRON_SWORD);
            ItemStack p1 = new ItemStack(Material.POTION, 3, (short) 16418);
            inv.addItem(sword, p1);

            inv.setHelmet(new ItemStack(Material.IRON_HELMET));
            inv.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            return;
        }
        PlayerInventory inv = player.getInventory();
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        ItemStack p1 = new ItemStack(Material.POTION, 2, (short) 16450);
        inv.addItem(sword, p1);

        inv.setHelmet(new ItemStack(Material.IRON_HELMET));
        inv.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        inv.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));


    }

}
