package cz.foresttech.forestsit.command;

import cz.foresttech.forestsit.ForestSit;
import cz.foresttech.forestsit.utils.colors.ColorAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForestSitCommand implements CommandExecutor {
    private ForestSit forestSit;

    public ForestSitCommand() {
        this.forestSit = ForestSit.getInstance();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) {
            forestSit.getLogger().warning("Command can be used only from game!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("config") && args[1].equalsIgnoreCase("reload") && player.hasPermission(forestSit.getForestSitManager().getPermsAdmin())) {
                forestSit.reloadConfig();
                forestSit.saveConfig();
                forestSit.getForestSitManager().loadData();
                player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getConfigReload()));
                return true;
            }
            player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getWrongSyntax()));
            return true;
        }
        forestSit.getForestSitController().sit(player);
        return true;
    }


}
