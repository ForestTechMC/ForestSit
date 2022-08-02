package cz.foresttech.forestsit.manager;

import com.tchristofferson.configupdater.ConfigUpdater;
import cz.foresttech.forestsit.ForestSit;
import cz.foresttech.forestsit.config.ConfigOptions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ForestSitManager {
    private ForestSit forestSit;

    private HashMap<String, ArmorStand> armorStandHashMap;

    private String noPerm = null;
    private String successSit = null;
    private String alreadySitting = null;
    private String playerInAirError = null;
    private String configReload = null;
    private String wrongSyntax = null;

    private boolean eventStairsSit = false;

    private String permsAdmin = null;
    private String permsCommandUse = null;
    private String permsEventUse = null;


    private String materials;

    /*-----------------------------------------------------------------------------*/

    public ForestSitManager() {
        this.forestSit = ForestSit.getInstance();
        loadConfig();
        loadData();
        //HashMap for save data of armorstand, and player
        armorStandHashMap = new HashMap<>();

    }


    public void loadConfig() {
        if (!forestSit.getDataFolder().exists())
            forestSit.getDataFolder().mkdir();
        forestSit.saveDefaultConfig();
        File file = new File(ForestSit.getInstance().getDataFolder() + "/config.yml");
        try {
            ConfigUpdater.update(ForestSit.getInstance(), "config.yml", file, new ArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        forestSit.reloadConfig();
    }


    /*-----------------------------------------------------------------------------*/

    /**
     *
     * Data method
     *
     */
    public void loadData() {
        //Config setup

        materials = forestSit.getConfig().getString(ConfigOptions.MATERIALS);
        eventStairsSit = forestSit.getConfig().getBoolean(ConfigOptions.EVENT_SIT);


        permsAdmin = forestSit.getConfig().getString(ConfigOptions.PERM_ADMIN);
        permsCommandUse = forestSit.getConfig().getString(ConfigOptions.PERM_COMMAND_USE);
        permsEventUse = forestSit.getConfig().getString(ConfigOptions.PERM_EVENT_USE);

        wrongSyntax = forestSit.getConfig().getString(ConfigOptions.WRONG_SYNTAX);
        playerInAirError = forestSit.getConfig().getString(ConfigOptions.PLAYER_IN_AIR);
        noPerm = forestSit.getConfig().getString(ConfigOptions.NO_PERM);
        successSit = forestSit.getConfig().getString(ConfigOptions.SUCCESS_SIT);
        alreadySitting = forestSit.getConfig().getString(ConfigOptions.ALREADY_SITTING);
        configReload = forestSit.getConfig().getString(ConfigOptions.CONFIG_RELOAD);

    }


    /*-----------------------------------------------------------------------------*/

    /**
     *
     * Method for Commands
     *
     * @param player want to sit
     */
    public void sit(Player player) {
        Location location = player.getLocation();

        ArmorStand seat = (ArmorStand)location.getWorld().spawn(location.clone().subtract(0.0D, 1.7D, 0.0D), ArmorStand.class);
        seat.setGravity(false);
        seat.setVisible(false);
        seat.setInvulnerable(true);
        armorStandHashMap.put(player.getName().toLowerCase(), seat);
        seat.setPassenger(player);
    }

    /*-----------------------------------------------------------------------------*/

    /**
     *
     * Method for Events
     *
     * @param player who clicked
     * @param clickedBlockLocation block from event
     */
    public void sitForEvent(Player player, Location clickedBlockLocation) {
        Location newLocation = clickedBlockLocation.getBlock().getLocation().add(0.5,0.2, 0.5);

        Arrow arrow = (Arrow) newLocation.getWorld().spawn(newLocation, Arrow.class);
        Location lastLocation = arrow.getLocation().clone().subtract(0.0D, 1.4D, 0.0D);
        ArmorStand seat = (ArmorStand)clickedBlockLocation.getWorld().spawn(lastLocation, ArmorStand.class);

        arrow.remove();

        seat.setGravity(false);
        seat.setVisible(false);
        seat.setInvulnerable(true);
        armorStandHashMap.put(player.getName().toLowerCase(), seat);
        seat.setPassenger(player);
    }

    /*-----------------------------------------------------------------------------*/

    public void removeSeat(Player player) {
        ArmorStand seat = armorStandHashMap.get(player.getName().toLowerCase());
        seat.remove();
    }

    public ForestSit getForestSit() {
        return forestSit;
    }

    public HashMap<String, ArmorStand> getArmorStandHashMap() {
        return armorStandHashMap;
    }

    public String getNoPerm() {
        return noPerm;
    }

    public String getSuccessSit() {
        return successSit;
    }

    public String getAlreadySitting() {
        return alreadySitting;
    }

    public String getPlayerInAirError() {
        return playerInAirError;
    }

    public String getConfigReload() {
        return configReload;
    }

    public boolean isEventStairsSit() {
        return eventStairsSit;
    }

    public String getPermsAdmin() {
        return permsAdmin;
    }

    public String getPermsCommandUse() {
        return permsCommandUse;
    }

    public String getPermsEventUse() {
        return permsEventUse;
    }

    public String getMaterials() {
        return materials;
    }

    public String getWrongSyntax() {
        return wrongSyntax;
    }
}
