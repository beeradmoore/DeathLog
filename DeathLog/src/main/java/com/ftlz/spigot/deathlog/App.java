package com.ftlz.spigot.deathlog;

import java.io.FileOutputStream;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class App extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getLogger().info("DeathLog Loaded");
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable()
    {
        getLogger().info("DeathLog Unloaded");
    }
}
