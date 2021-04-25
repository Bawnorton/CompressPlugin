package com.bawnorton.compress.events;

import com.bawnorton.compress.Compress;
import com.bawnorton.compress.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

public class CompressEvents implements Listener {

    public CompressEvents(Compress plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if ((event.getItemInHand().getItemMeta().getDisplayName().contains("Compressed"))) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.RED + "You cannot place this block");
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        ItemStack[] ingredients;
        try {
            ingredients = event.getInventory().getMatrix();
        } catch (NullPointerException ignore) { return; }
        boolean compressedBlock = true;
        boolean oneIngredient = false;
        Material type = Material.AIR;
        for (ItemStack ingredient : ingredients) {
            if (ingredient == null) {
                continue;
            }
            else if (oneIngredient) {
                return;
            }
            else {
                oneIngredient = true;
            }
            for(Object[] block: ItemManager.compressedBlocks) {
                if(ingredient.getType() == block[0]) {
                    if(!(ingredient.getItemMeta().getDisplayName().contains("Compressed"))) {
                        type = (Material) block[0];
                        compressedBlock = false;
                        break;
                    }
                }
            }
        }
        if (!compressedBlock) {
            switch(type) {
                case ACACIA_LOG:
                    event.getInventory().setResult(new ItemStack(Material.ACACIA_PLANKS, 4));
                    break;
                case BIRCH_LOG:
                    event.getInventory().setResult(new ItemStack(Material.BIRCH_PLANKS, 4));
                    break;
                case CRIMSON_STEM:
                    event.getInventory().setResult(new ItemStack(Material.CRIMSON_PLANKS, 4));
                    break;
                case DARK_OAK_LOG:
                    event.getInventory().setResult(new ItemStack(Material.DARK_OAK_PLANKS, 4));
                    break;
                case JUNGLE_LOG:
                    event.getInventory().setResult(new ItemStack(Material.JUNGLE_PLANKS, 4));
                    break;
                case OAK_LOG:
                    event.getInventory().setResult(new ItemStack(Material.OAK_PLANKS, 4));
                    break;
                case SPRUCE_LOG:
                    event.getInventory().setResult(new ItemStack(Material.SPRUCE_PLANKS, 4));
                    break;
                case STONE:
                    event.getInventory().setResult(new ItemStack(Material.STONE_BUTTON));
                    break;
                case WARPED_STEM:
                    event.getInventory().setResult(new ItemStack(Material.WARPED_PLANKS, 4));
                    break;
                case ANDESITE:
                case BASALT:
                case BLACKSTONE:
                case COBBLESTONE:
                case DIORITE:
                case DIRT:
                case END_STONE:
                case GRANITE:
                case GRAVEL:
                case NETHERRACK:
                case MAGMA_BLOCK:
                case OBSIDIAN:
                case SAND:
                case SANDSTONE:
                case SOUL_SAND:
                case SOUL_SOIL:
                    event.getInventory().setResult(null);
                    break;
                default:
                    break;
            }
        }
    }
}
