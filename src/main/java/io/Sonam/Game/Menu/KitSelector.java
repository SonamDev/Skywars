package io.Sonam.Game.Menu;

import io.Sonam.profiler.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONObject;

public class KitSelector {

    public boolean championUnlocked;
    public boolean archerUnlocked;
    public boolean knightUnlocked;
    public boolean chemistUnlocked;
    public boolean pyroUnlocked;
    public boolean armorerUnlocked;
    public boolean scoutUnlocked;


    public ItemStack champion(boolean unlocked) {
        this.championUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.STONE_SWORD) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Champion Kit" : ChatColor.RED + "Champion Kit");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack archer(boolean unlocked) {
        this.archerUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.BOW) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Archer Kit" : ChatColor.RED + "Archer Kit");
        item.setItemMeta(meta);
        return item;
    }

    public  ItemStack knight(boolean unlocked) {
        this.knightUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.IRON_SWORD) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Knight Kit" : ChatColor.RED + "Knight Kit");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack chemist(boolean unlocked) {
        this.chemistUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.POTION, 1, (short) 16388) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Chemist Kit" : ChatColor.RED + "Chemist Kit");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack pyro(boolean unlocked) {
        this.pyroUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.FLINT_AND_STEEL) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Pyro Kit" : ChatColor.RED + "Pyro Kit");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack armorer(boolean unlocked) {
        this.armorerUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.GOLD_CHESTPLATE) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Armorer Kit" : ChatColor.RED + "Armorer Kit");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack scout(boolean unlocked) {
        this.scoutUnlocked = unlocked;
        ItemStack item = unlocked ? new ItemStack(Material.POTION, 1, (short) 8258) : new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(unlocked ? ChatColor.GREEN + "Scout Kit" : ChatColor.RED + "Scout Kit");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        return item;
    }

    private Inventory selector(PlayerProfile profile) {
        JSONObject object = new JSONObject(profile.getPlayerObject().toJSONString()).getJSONObject("gameMeta").getJSONObject("SKYWARS").getJSONObject("kits");
        Inventory inv = Bukkit.createInventory(null, 27, "Kit Selector");
        inv.setItem(10, champion(object.getJSONObject("champion").getBoolean("unlocked")));
        inv.setItem(11, archer(object.getJSONObject("archer").getBoolean("unlocked")));
        inv.setItem(12, knight(object.getJSONObject("knight").getBoolean("unlocked")));
        inv.setItem(13, chemist(object.getJSONObject("chemist").getBoolean("unlocked")));
        inv.setItem(14, pyro(object.getJSONObject("pyro").getBoolean("unlocked")));
        inv.setItem(15, armorer(object.getJSONObject("armorer").getBoolean("unlocked")));
        inv.setItem(16, scout(object.getJSONObject("scout").getBoolean("unlocked")));
        return inv;
    }

    public Inventory getSelector(PlayerProfile profile) {
        return selector(profile);
    }


}
