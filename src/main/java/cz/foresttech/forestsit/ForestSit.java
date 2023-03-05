package cz.foresttech.forestsit;

import cz.foresttech.forestsit.command.ForestSitCommand;
import cz.foresttech.forestsit.controller.ForestSitController;
import cz.foresttech.forestsit.listener.ForestSitListener;
import cz.foresttech.forestsit.manager.ForestSitManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ForestSit extends JavaPlugin {
    private static ForestSit instance;

    private ForestSitManager forestSitManager;
    private ForestSitController forestSitController;

    /*-----------------------------------------------------------------------------*/

    @Override
    public void onEnable() {
        instance = this;
        forestSitManager = new ForestSitManager();
        forestSitController = new ForestSitController();

        registerListener(new ForestSitListener());
        registerCommand(new ForestSitCommand(), "forestsit");

        getLogger().info(" ");
        getLogger().info(" ForestSit v1.0.0 | Enabled");
        getLogger().info("  ");
        getLogger().info("   Author: Fly_Ultra");
        getLogger().info("   Version: 1.0.0");
        getLogger().info("   Spigot");
        getLogger().info(" ");
    }

    /*-----------------------------------------------------------------------------*/

    @Override
    public void onDisable() {
        getLogger().warning(" ");
        getLogger().warning(" ForestSit v1.0.0 | Disabled");
        getLogger().warning("  ");
        getLogger().warning("   Author: Fly_Ultra");
        getLogger().warning("   Version: 1.0.0");
        getLogger().warning("   Spigot");
        getLogger().warning(" ");
    }

    /*-----------------------------------------------------------------------------*/

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, ForestSit.getInstance());

    }

    /*-----------------------------------------------------------------------------*/

    public void registerCommand(CommandExecutor command, String commandName) {
        getCommand(commandName).setExecutor(command);

    }

    /*-----------------------------------------------------------------------------*/

    public static ForestSit getInstance() {
        return instance;
    }

    public ForestSitManager getForestSitManager() {
        return forestSitManager;
    }

    public ForestSitController getForestSitController() {
        return forestSitController;
    }
}
