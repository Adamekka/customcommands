package com.adamekka.customcommands;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;
    private static final Logger LOGGER = Logger.getLogger("customcommands");
    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;

        LOGGER.info("customcommands enabled");
    }

    @Override
    public void onDisable() {
        LOGGER.info("customcommands disabled");
    }
}
