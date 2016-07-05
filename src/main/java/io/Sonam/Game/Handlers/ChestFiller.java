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
            inv.setItem(1, new ItemStack(Material.DIAMOND));

        }
    }

}
