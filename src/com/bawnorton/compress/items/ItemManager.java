package com.bawnorton.compress.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class ItemManager {

    public static final Object[][] compressedBlocks = {
            {Material.ACACIA_LOG, "Compressed Acacia Log", "Acacia Log", null},
            {Material.ANDESITE, "Compressed Andesite" , "Andesite", null},
            {Material.BASALT, "Compressed Basalt" , "Basalt", null},
            {Material.BIRCH_LOG, "Compressed Birch Log" , "Birch Log", null},
            {Material.BLACKSTONE, "Compressed Blackstone" , "Blackstone", null},
            {Material.COBBLESTONE, "Compressed Cobblestone" , "Cobblestone", null},
            {Material.CRIMSON_STEM, "Compressed Crimson Stem" , "Crimson Stem", null},
            {Material.DARK_OAK_LOG, "Compressed Dark Oak Log", "Dark Oak Log", null},
            {Material.DIORITE, "Compressed Diorite" , "Diorite", null},
            {Material.DIRT, "Compressed Dirt" , "Dirt", null},
            {Material.END_STONE, "Compressed End Stone" , "End Stone", null},
            {Material.GRANITE, "Compressed Granite" , "Granite", null},
            {Material.GRAVEL, "Compressed Gravel" , "Gravel", null},
            {Material.JUNGLE_LOG, "Compressed Jungle Log", "Jungle Log", null},
            {Material.NETHERRACK, "Compressed Netherrack" , "Netherrack", null},
            {Material.MAGMA_BLOCK, "Compressed Magma Block" , "Magma Block", null},
            {Material.OBSIDIAN, "Compressed Obsidian" , "Obsidian", null},
            {Material.OAK_LOG, "Compressed Oak Log" , "Oak Log", null},
            {Material.RED_SAND, "Compressed Red Sand" , "Red Sand", null},
            {Material.SAND, "Compressed Sand" , "Sand", null},
            {Material.SANDSTONE, "Compressed Sandstone" , "Sandstone", null},
            {Material.SOUL_SAND, "Compressed Soul Sand" , "Soul Sand", null},
            {Material.SOUL_SOIL, "Compressed Soul Soil" , "Soul Soil", null},
            {Material.SPRUCE_LOG, "Compressed Spruce Log" , "Spruce Log", null},
            {Material.STONE, "Compressed Stone" , "Stone", null},
            {Material.WARPED_STEM, "Compressed Warped Stem" , "Warped Stem", null}
    };

    public static void init() {
        createCompressed();
    }

    private static void createCompressed() {
        for(int i = 0; i < compressedBlocks.length; i++) {
            ItemStack item = new ItemStack((Material) compressedBlocks[i][0], 1);
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName((String) compressedBlocks[i][1]);
            List<String> lore = new ArrayList<>();
            lore.add("This is a 3x3 block of " + compressedBlocks[i][2]);
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
            compressedBlocks[i][3] = item;
            ShapelessRecipe compress = new ShapelessRecipe(NamespacedKey.minecraft("compressed_"+((String)compressedBlocks[i][2]).toLowerCase().replace(" ", "")), item);
            compress.addIngredient(9, (Material) compressedBlocks[i][0]);
            getServer().addRecipe(compress);
            ShapelessRecipe uncompress = new ShapelessRecipe(NamespacedKey.minecraft("uncompressed_"+((String)compressedBlocks[i][2]).toLowerCase().replace(" ", "")), new ItemStack((Material) compressedBlocks[i][0], 9));
            uncompress.addIngredient((Material) compressedBlocks[i][0]);
            getServer().addRecipe(uncompress);
        }
    }

}
