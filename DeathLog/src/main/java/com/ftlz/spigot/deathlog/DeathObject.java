package com.ftlz.spigot.deathlog;

import com.google.gson.annotations.SerializedName;

import org.bukkit.Location;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathObject
{
    @SerializedName("name")
    private String _playerName;

    @SerializedName("killer")
    private String _killerName;

    @SerializedName("location")
    private Location _location;

    @SerializedName("death_message")
    private String _deathMessage;

    @SerializedName("time")
    private long _time;

    public DeathObject(PlayerDeathEvent playerDeathEvent)
    {
        Player player = playerDeathEvent.getEntity();
        _playerName = player.getName();
        _location = player.getLocation();
        String deathMessage = playerDeathEvent.getDeathMessage();
        _deathMessage = (deathMessage == null) ? "" : deathMessage;
        _time = (System.currentTimeMillis() / 1000L);
        Player killer = player.getKiller();
        _killerName = (killer == null) ? "" : killer.getName();
    }
}