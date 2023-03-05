package cz.foresttech.forestsit.config;

import com.google.common.base.Charsets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigAPI {

    // Your plugin
    private JavaPlugin plugin;

    // Name of the file
    private String fileName;

    // File from fileName
    private File file;

    private File folder;

    // The File configuration, for options like normal config
    private FileConfiguration configuration;

    /**
     *
     * Constructor for config
     *
     * @param plugin Your plugin
     * @param fileName name of the file, we want to modify
     */
    public ConfigAPI(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        if (!fileName.contains(".yml")) {
            fileName = fileName + ".yml";
        }

        this.fileName = fileName;
        file = new File(plugin.getDataFolder(), fileName);
    }

    /**
     *
     * This only create the config, and reload
     *
     */
    public void create() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        reload();
    }


    /**
     *
     * This reload config where we use input reader
     *
     */
    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            configuration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        }
    }

    /**
     *
     * This is the essential method for using the whole config
     *
     * @return options like normal config
     */
    public FileConfiguration getConfig() {
        configuration = YamlConfiguration.loadConfiguration(file);
        return configuration;
    }

    /**
     *
     * Normal save by configuration
     * File -> the file we modify
     *
     */
    public void save() {
        try {
            configuration.save(file);
        } catch (IOException ignored) {
        }
    }

}
