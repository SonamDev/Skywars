package io.Sonam.Game.Handlers;

import io.Sonam.Game.SkyWars;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ChestFiller {

    public void randomizeChests() {
        for(Block block : SkyWars.getChests()) {
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getInventory();
            ArrayList<Integer> intArray = new ArrayList<Integer>(14);
            for (int i = 0; i < intArray.size() ; i++) {
                intArray.add(i);
            }
            Collections.shuffle(intArray);
            Random random = new Random();
            for(int i : intArray) {
                int integ = random.nextInt(15);
                switch (integ) {
                    case 1:
                        inventory.setItem(i, new ItemStack(Material.LOG, 32));
                        break;
                    case 14:
                        inventory.setItem(i, new ItemStack(Material.GOLDEN_APPLE));
                        break;
                    case 3:
                        inventory.setItem(i, new ItemStack(Material.DIAMOND_PICKAXE));
                        break;
                    case 4:
                        inventory.setItem(i, new ItemStack(Material.IRON_AXE));
                        break;
                    case 5:
                        inventory.setItem(i, new ItemStack(Material.STONE, 64));
                        break;
                    case 6:
                        ItemStack sword = new ItemStack(Material.STONE_SWORD);
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        inventory.setItem(i, sword);
                        break;
                    case 7:
                        inventory.setItem(i, new ItemStack(Material.COOKED_BEEF, 32));
                        break;
                }
            }
        }
    }

}
