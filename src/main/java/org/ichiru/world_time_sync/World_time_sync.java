package org.ichiru.world_time_sync;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public final class World_time_sync extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        syncWorldTimes();
    }

    private void syncWorldTimes() {
        String worldName1 = getConfig().getString("worlds.world1");
        String worldName2 = getConfig().getString("worlds.world2");

        World world1 = Bukkit.getWorld(Objects.requireNonNull(worldName1));
        World world2 = Bukkit.getWorld(Objects.requireNonNull(worldName2));

        if (world1 != null && world2 != null) {
            new BukkitRunnable() {
                public void run() {
                    long time = world1.getTime();
                    world2.setTime(time);
                }
            }.runTaskTimer(this, 0L, 20L);
        } else {
            getLogger().warning("One of the worlds is not loaded!");
        }
    }
}
