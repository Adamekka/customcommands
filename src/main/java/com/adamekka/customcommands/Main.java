package com.adamekka.customcommands;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("customcommands");

    public void onEnable() {
        LOGGER.info("customcommands enabled");
    }

    public void onDisable() {
        LOGGER.info("customcommands disabled");
    }
}
