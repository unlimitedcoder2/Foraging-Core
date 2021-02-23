package me.tech.foraging;

import me.tech.foraging.items.ItemManager;
import me.tech.foraging.models.player.ForagingPlayer;
import me.tech.foraging.player.events.JoinEvent;
import me.tech.foraging.player.events.QuitEvent;
import me.tech.foraging.player.events.RegionEnterEvent;
import me.tech.foraging.regions.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Foraging extends JavaPlugin {
	public ItemManager itemManager;
	public RegionManager regionManager;
	public PluginManager pluginManager = getServer().getPluginManager();
	public HashMap<UUID, ForagingPlayer> players = new HashMap<>();

	private final HashMap<String, FileConfiguration> configs = new HashMap<>();

	@Override
	public void onEnable() {
		// Make sure required plugins are actually loaded onto the server.
		if(!pluginManager.isPluginEnabled("HolographicDisplays") || !pluginManager.isPluginEnabled("WorldGuard")) {
			getLogger().severe("HolographicDisplays or WorldGuard was not loaded into the server, automatically disabling plugin.");
			pluginManager.disablePlugin(this);

			return;
		}
		// Initialize everything pls
		this.initConfigs();
		this.initCommands();
		this.initEvents();

		this.itemManager = new ItemManager(this);
		this.regionManager = new RegionManager(this);
	}

	@Override
	public void onDisable() { }

	/**
	 * Initialize all commands.
	 */
	private void initCommands() { }

	/**
	 * Initialize all events.
	 */
	private void initEvents() {
		pluginManager.registerEvents(new JoinEvent(this), this);
		pluginManager.registerEvents(new QuitEvent(this), this);
		pluginManager.registerEvents(new RegionEnterEvent(this), this);
	}

	/**
	 * Initialize all configurations custom configurations.
	 */
	private void initConfigs() {
		String[] configurations = {"config", "items", "monsters", "regions"};

		for(String configName : configurations) {
			File file = new File(getDataFolder(), String.format("%s.yml", configName));

			if(!file.exists()) {
				this.saveResource(String.format("%s.yml", configName), false);
			}

			FileConfiguration configuration = new YamlConfiguration();
			try {
				configuration.load(file);
				// I want to add a check here to make sure
				// the hashmap actually contains this config but IntellIJ
				// is saying fuck that check so I'm going to believe it.
				this.configs.remove(configName);
				this.configs.put(configName, configuration);
			} catch(IOException | InvalidConfigurationException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Get a specific configuration.
	 * @param name Configuration name.
	 * @return FileConfiguration.
	 */
	public FileConfiguration getConfiguration(String name) {
		// Just return main config.
		if(name.equalsIgnoreCase("config")) return this.getConfig();
		// Check to make sure the config is actually added.
		if(this.configs.containsKey(name)) return this.configs.get(name);
		else return null;
	}
}
