package io.Sonam.Game.Handlers;

import io.Sonam.Game.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
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
            ItemStack[] items = new ItemStack[27];
            for(int i = 0; i < items.length; i++) {
                switch (random.nextInt(30)) {
                    case 1:
                        items[i] = new ItemStack(Material.LOG, 32);
                        continue;
                    case 2:
                        items[i] = new ItemStack(Material.STONE, 64);
                        continue;
                    case 3:
                        items[i] = new ItemStack(Material.STONE_SWORD);
                        continue;
                    case 4:
                        items[i] = new ItemStack(Material.DIAMOND_PICKAXE);
                        continue;
                    case 5:
                        items[i] = new ItemStack(Material.STONE_AXE);
                        continue;
                    case 6:
                        items[i] = new ItemStack(Material.GOLDEN_APPLE);
                        continue;
                    case 7:
                        items[i] = new ItemStack(Material.POTION, 1, (short) 8226);
                        continue;
                    default:
                        items[i] = new ItemStack(Material.AIR);
                }
            }
            inv.setContents(items);

        }
    }

}
