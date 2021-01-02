package com.bawnorton.compress;

import com.bawnorton.compress.events.CompressEvents;
import com.bawnorton.compress.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Compress extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        new CompressEvents(this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Compress]: Plugin Enabled");
        ItemManager.init();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Compress]: Plugin Disabled");
    }
}