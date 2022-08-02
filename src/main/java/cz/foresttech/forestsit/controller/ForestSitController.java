package cz.foresttech.forestsit.controller;

import cz.foresttech.forestsit.ForestSit;
import cz.foresttech.forestsit.utils.colors.ColorAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ForestSitController {
    private ForestSit forestSit;


    public ForestSitController() {
        this.forestSit = ForestSit.getInstance();
    }

    public void sit(Player player) {
        if (!player.hasPermission(forestSit.getForestSitManager().getPermsAdmin()) && !player.hasPermission(forestSit.getForestSitManager().getPermsCommandUse())) {
            player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getNoPerm()));
            return;
        }

        if (player.getLocation().subtract(0,1,0).getBlock().getType() == Material.AIR){
            player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getPlayerInAirError()));
            return;
        }

        if (forestSit.getForestSitManager().getArmorStandHashMap().containsKey(player.getName())) {
            player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getAlreadySitting()));
            return;
        }


        forestSit.getForestSitManager().sit(player);
        player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getSuccessSit()));

    }

    public void sitForEvent(Player player, Location locationOfBlockEvent) {
        if (!player.hasPermission(forestSit.getForestSitManager().getPermsAdmin()) && !player.hasPermission(forestSit.getForestSitManager().getPermsEventUse())) {
            return;
        }

        if (player.getPassengers().size() != 0) {
            player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getAlreadySitting()));
            return;
        }

        forestSit.getForestSitManager().sitForEvent(player, locationOfBlockEvent);
        player.sendMessage(ColorAPI.colorize(forestSit.getForestSitManager().getSuccessSit()));

    }

}
