package com.adamekka.customcommands;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

class CustomCommand {
    public String command;
    public List<String> executes;
    public String description;
    public List<String> aliases;

    public CustomCommand(String command, List<String> executes, String description, List<String> aliases) {
        this.command = command;
        this.executes = executes;
        this.description = description;
        this.aliases = aliases;
    }
}

public class Main extends JavaPlugin {

    private static ArrayList<CustomCommand> customCommands;

    private static final Logger LOGGER = Logger.getLogger("customcommands");

    @Override
    public void onEnable() {
        LOGGER.info("[Custom-Commands] Starting to load Plugin...");
        loadConfig();

        customCommands = new ArrayList<>();

        readConfig();
        registerCommands();
        for (CustomCommand customCommand : customCommands) {
            System.out.println("[CustomCommands] Found custom command: Alias: " + customCommand.aliases + ", Command: "
                    + customCommand.executes);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,
            String[] args) {
        return super.onCommand(sender, command, label, args);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands() {
        try {
            final Field bukkitCommandMap = getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(getServer());

            // Custom commands
            for (CustomCommand customCommand : customCommands) {
                commandMap.register("customcommands",
                        new Command(customCommand.command, customCommand.description, "/<command>",
                                customCommand.aliases) {

                            @Override
                            public boolean execute(CommandSender sender, String arg1, String[] arg2) {
                                Player p = ((sender instanceof Player) ? (Player) sender : null);
                                if (p == null) {
                                    sender.sendMessage("Custom-Commands only work with players");
                                    return false;
                                }
                                for (String index : customCommand.executes) {
                                    p.performCommand(index);
                                }
                                return true;
                            }
                        });
            }

        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readConfig() {
        ConfigurationSection commands = getConfig().getConfigurationSection("commands");
        for (String commandName : commands.getKeys(false)) {
            LOGGER.info("[Custom-Commands] Found command \"" + commandName + "\"");
            customCommands.add(new CustomCommand(
                    getConfig().getString("commands." + commandName),
                    getConfig().getStringList("commands." + commandName + ".executes"),
                    getConfig().getString("commands." + commandName + ".description"),
                    getConfig().getStringList("commands." + commandName + ".aliases")));
        }
    }
}
