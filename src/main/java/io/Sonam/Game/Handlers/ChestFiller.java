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

public class ChestFiller {

    public void randomizeChests() {
        int number = 0;
        for(Location loc : SkyWars.getChests()) {
            number++;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Filling chest " + number);
            Chest chest = (Chest) loc.getBlock().getState();
            Inventory inv = chest.getInventory();
            ArrayList<Integer> intArray = new ArrayList<Integer>(14);
            for (int i = 0; i < intArray.size() ; i++) {
                intArray.add(i);
            }
            Collections.shuffle(intArray);
            inv.setItem(0, new ItemStack(Material.LOG));
        }
    }

}
