package io.Sonam.Game.Handlers;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ChestFiller {

    public void randomizeChests() {
        Random random = new Random();
        int number = 0;
        for(Location loc : SkyWars.getChests()) {
            number++;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Filling chest " + number);
            Chest chest = (Chest) loc.getBlock().getState();
            Inventory inv = chest.getInventory();
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Got CHEST_INVENTORY");
            ArrayList<Integer> intArray = new ArrayList<Integer>();
            for (int i = 0; i < 27 ; i++) {
                intArray.add(i);
            }
            Collections.shuffle(intArray);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Shuffled");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Randomizing...");
            for(int i : intArray) {
                Bukkit.broadcastMessage(i + "");
                int integ = random.nextInt(15);
                Bukkit.broadcastMessage(ChatColor.YELLOW + "RND: " + integ);
                switch (integ) {
                    case 1:
                        inv.setItem(i, new ItemStack(Material.LOG, 32));
                        Bukkit.getLogger().info("Filled with log " + number);
                    case 14:
                        inv.setItem(i, new ItemStack(Material.GOLDEN_APPLE));
                        Bukkit.getLogger().info("Filled with gapple " + number);
                    case 3:
                        inv.setItem(i, new ItemStack(Material.DIAMOND_PICKAXE));
                        Bukkit.getLogger().info("Filled with d pick " + number);
                    case 4:
                        inv.setItem(i, new ItemStack(Material.IRON_AXE));
                        Bukkit.getLogger().info("Filled with iron pick " + number);
                    case 5:
                        inv.setItem(i, new ItemStack(Material.STONE, 64));
                        Bukkit.getLogger().info("Filled with stone " + number);
                    case 6:
                        ItemStack sword = new ItemStack(Material.STONE_SWORD);
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        inv.setItem(i, sword);
                        Bukkit.getLogger().info("Filled with stone sword " + number);
                    case 7:
                        inv.setItem(i, new ItemStack(Material.COOKED_BEEF, 32));
                        Bukkit.getLogger().info("Filled with steak " + number);
                    default:
                        inv.setItem(i, new ItemStack(Material.AIR));
                }
            }
        }
    }

}
