package gg.gianluca.cacheexample;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import gg.gianluca.cacheexample.commands.TPACommand;
import gg.gianluca.cacheexample.commands.TPAcceptCommand;
import gg.gianluca.cacheexample.commands.TPDenyCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class CacheExample extends JavaPlugin {

    private Cache<UUID, UUID> teleportCache;

    @Override
    public void onEnable() {
        teleportCache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .maximumSize(500)
                .build();

        getCommand("tpa").setExecutor(new TPACommand(this));
        getCommand("tpaccept").setExecutor(new TPAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TPDenyCommand(this));
    }

    @Override
    public void onDisable() {
        teleportCache = null;
    }

    public Cache<UUID, UUID> getTeleportCache() {
        return teleportCache;
    }
}