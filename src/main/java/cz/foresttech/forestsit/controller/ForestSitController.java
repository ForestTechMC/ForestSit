package cz.foresttech.forestsit.controller;

import cz.foresttech.forestsit.ForestSit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import cz.foresttech.api.ColorAPI;

public class ForestSitController {


    private ForestSit plugin;

    public ForestSitController() {
        this.plugin = ForestSit.getInstance();
    }

    public void reloadData(Player player) {
        plugin.getForestSitManager().reloadConfig();
        player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getConfigReload()));

    }

    /**
     *
     * This is controller method for sit
     * Here we subtract the location
     *
     * @param player
     */
    public void sit(Player player) {
        if (!player.hasPermission(plugin.getForestSitManager().getPermsAdmin()) && !player.hasPermission(plugin.getForestSitManager().getPermsCommandUse())) {
            player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getNoPerm()));
            return;
        }

        if (player.getLocation().subtract(0,1,0).getBlock().getType() == Material.AIR){
            player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getPlayerInAirError()));
            return;
        }

        if (plugin.getForestSitManager().getArmorStandHashMap().containsKey(player.getName())) {
            player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getAlreadySitting()));
            return;
        }


        plugin.getForestSitManager().sit(player);
        player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getSuccessSit()));

    }

    /**
     *
     * Sit method for event, here we control the passenger size
     * and calling location for clicked block
     *
     *
     */
    public void sitForEvent(Player player, Location locationOfBlockEvent) {
        if (!player.hasPermission(plugin.getForestSitManager().getPermsAdmin()) && !player.hasPermission(plugin.getForestSitManager().getPermsEventUse())) {
            return;
        }

        if (player.getPassengers().size() != 0) {
            player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getAlreadySitting()));
            return;
        }

        plugin.getForestSitManager().sitForEvent(player, locationOfBlockEvent);
        player.sendMessage(ColorAPI.colorize(plugin.getForestSitManager().getSuccessSit()));

    }

}
