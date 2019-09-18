package gg.gianluca.cacheexample.commands;

import gg.gianluca.cacheexample.CacheExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAcceptCommand implements CommandExecutor {

    private CacheExample plugin;

    public TPAcceptCommand(CacheExample plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (plugin.getTeleportCache().getIfPresent(player.getUniqueId()) == null) {
            player.sendMessage("You have no teleport requests!");
            return true;
        }

        Player target = Bukkit.getPlayer(plugin.getTeleportCache().getIfPresent(player.getUniqueId()));

        if (target == null) {
            player.sendMessage("That player just went offline");
            return true;
        }

        target.teleport(player.getLocation());
        plugin.getTeleportCache().invalidate(player.getUniqueId());
        player.sendMessage("You accepted the request!");
        target.sendMessage("They accepted the request!");

        return true;
    }
}