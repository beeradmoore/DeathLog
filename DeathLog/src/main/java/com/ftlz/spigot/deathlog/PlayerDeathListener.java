package com.ftlz.spigot.deathlog;

import java.io.FileOutputStream;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
 
public class PlayerDeathListener implements Listener
{
    App _app;
    public PlayerDeathListener(App app)
    {
        _app = app;        
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event)
    {
        if (event.getDeathMessage() == null)
            return;
                
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Location.class, new LocationAdapter());
        final Gson gson = gsonBuilder.create();
        String jsonData = gson.toJson(new DeathObject(event));
        
        AppendToDeathlog(jsonData);
    }

    public void AppendToDeathlog(String data)
    {
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream("DeathLog.jsonl", true);

            java.nio.channels.FileLock lock = fileOutputStream.getChannel().lock();
            try
            {
                fileOutputStream.write((data + "\n").getBytes("utf-8"));
            }
            catch (Exception err)
            {
                _app.getLogger().log(Level.WARNING, err.getMessage());
            }
            finally
            {
                lock.release();
                fileOutputStream.close();
                fileOutputStream = null;
            }
        }
        catch (Exception err)
        {
            _app.getLogger().log(Level.WARNING, err.getMessage());
        }
        finally
        {
            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (Exception err)
                {
                    _app.getLogger().log(Level.WARNING, err.getMessage());
                }
            }
        }
    } 
}