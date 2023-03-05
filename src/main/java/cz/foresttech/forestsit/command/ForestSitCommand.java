package cz.foresttech.forestsit.command;

import cz.foresttech.forestsit.ForestSit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cz.foresttech.api.ColorAPI;

public class ForestSitCommand implements CommandExecutor {
    private ForestSit plugin;

    public ForestSitCommand() {
        this.plugin = ForestSit.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLogger().warning("Command can be used only from game!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("config") && args[1].equalsIgnoreCase("reload") && player.hasPermission(plugin.getForestSitManager().getPermsAdmin())) {
                plugin.getForestSitController().reloadData(player);
                return true;
            }
            player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getWrongSyntax()));
            return true;
        }
        plugin.getForestSitController().sit(player);
        return true;
    }

}
