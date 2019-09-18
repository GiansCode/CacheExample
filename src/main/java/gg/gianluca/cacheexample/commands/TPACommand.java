package gg.gianluca.cacheexample.commands;

import gg.gianluca.cacheexample.CacheExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {

    private CacheExample plugin;

    public TPACommand(CacheExample plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Incorrect usage! Please use /tpa <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("That player is not online!");
            return true;
        }

        if (player == target) {
            player.sendMessage("You cannot teleport to yourself!");
            return true;
        }

        plugin.getTeleportCache().put(target.getUniqueId(), player.getUniqueId());

        target.sendMessage("You have a teleportation request! Use /tpaccept or /tpdeny");
        player.sendMessage("You have sent a teleportation request!");

        return true;
    }
}