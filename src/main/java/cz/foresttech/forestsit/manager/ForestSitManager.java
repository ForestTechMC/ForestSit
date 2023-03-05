package cz.foresttech.forestsit.manager;

import cz.foresttech.forestsit.ForestSit;
import cz.foresttech.forestsit.config.ConfigAPI;
import cz.foresttech.forestsit.config.ConfigOptions;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ForestSitManager {

    private ForestSit plugin;

    private ConfigAPI configAPI;

    private HashMap<String, ArmorStand> armorStandHashMap;

    private String noPerm;
    private String successSit;
    private String alreadySitting;
    private String playerInAirError;
    private String configReload;
    private String wrongSyntax;

    private boolean eventStairsSit;

    private String permsAdmin;
    private String permsCommandUse;
    private String permsEventUse;


    private String materials;

    /*-----------------------------------------------------------------------------*/

    public ForestSitManager() {
        this.plugin = ForestSit.getInstance();
        loadConfig();
        loadData();

    }

    /**
     *
     * Load config from Config API
     *
     */
    public void loadConfig() {
        configAPI = new ConfigAPI(plugin, "config");
        configAPI.create();
    }


    /*-----------------------------------------------------------------------------*/

    /**
     *
     * Data method
     *
     */
    public void loadData() {
        armorStandHashMap = new HashMap<>();

        materials = configAPI.getConfig().getString(ConfigOptions.MATERIALS);
        eventStairsSit = configAPI.getConfig().getBoolean(ConfigOptions.EVENT_SIT);


        permsAdmin = configAPI.getConfig().getString(ConfigOptions.PERM_ADMIN);
        permsCommandUse = configAPI.getConfig().getString(ConfigOptions.PERM_COMMAND_USE);
        permsEventUse = configAPI.getConfig().getString(ConfigOptions.PERM_EVENT_USE);

        wrongSyntax = configAPI.getConfig().getString(ConfigOptions.WRONG_SYNTAX);
        playerInAirError = configAPI.getConfig().getString(ConfigOptions.PLAYER_IN_AIR);
        noPerm = configAPI.getConfig().getString(ConfigOptions.NO_PERM);
        successSit = configAPI.getConfig().getString(ConfigOptions.SUCCESS_SIT);
        alreadySitting = configAPI.getConfig().getString(ConfigOptions.ALREADY_SITTING);
        configReload = configAPI.getConfig().getString(ConfigOptions.CONFIG_RELOAD);

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
        ArmorStand seat = (ArmorStand) clickedBlockLocation.getWorld().spawn(lastLocation, ArmorStand.class);

        arrow.remove();

        seat.setGravity(false);
        seat.setVisible(false);
        seat.setInvulnerable(true);
        armorStandHashMap.put(player.getName().toLowerCase(), seat);
        seat.setPassenger(player);
    }

    /*-----------------------------------------------------------------------------*/

    /**
     *
     * Reload config method, with realoding data
     *
     */
    public void reloadConfig() {
        configAPI.reload();
        configAPI.save();
        plugin.getForestSitManager().loadData();
    }

    /*-----------------------------------------------------------------------------*/

    /**
     *
     * Remove armorstand from hashmap, and delete
     *
     */
    public void removeSeat(Player player) {
        ArmorStand seat = armorStandHashMap.get(player.getName().toLowerCase());
        seat.remove();
        armorStandHashMap.remove(player.getName().toLowerCase());
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
