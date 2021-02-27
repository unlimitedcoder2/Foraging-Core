package me.tech.foraging;

import me.tech.foraging.commands.CoolCommand;
import me.tech.foraging.commands.GiveItemCommand;
import me.tech.foraging.commands.ReloadConfigurationsCommand;
import me.tech.foraging.database.Database;
import me.tech.foraging.items.ItemManager;
import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterEquipment;
import me.tech.foraging.models.monsters.ForagingMonsterStats;
import me.tech.foraging.models.player.ForagingPlayer;
import me.tech.foraging.monsters.Monster;
import me.tech.foraging.monsters.Zombie;
import me.tech.foraging.monsters.events.MonsterDeathEvent;
import me.tech.foraging.player.events.JoinEvent;
import me.tech.foraging.player.events.QuitEvent;
import me.tech.foraging.player.events.RegionEnterEvent;
import me.tech.foraging.regions.RegionManager;
import me.tech.foraging.utils.LangManager;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Foraging extends JavaPlugin {
	public ItemManager itemManager;
	public RegionManager regionManager;
	public PluginManager pluginManager = getServer().getPluginManager();
	public LangManager langManager;
	public Database database;

	public HashMap<UUID, ForagingPlayer> players = new HashMap<>();
	public HashMap<UUID, Monster> monsters = new HashMap<>();
	private final HashMap<String, FileConfiguration> configs = new HashMap<>();

	@Override
	public void onEnable() {
		// Make sure required plugins are actually loaded onto the server.
		if(!pluginManager.isPluginEnabled("HolographicDisplays") || !pluginManager.isPluginEnabled("WorldGuard")) {
			getLogger().severe("HolographicDisplays or WorldGuard was not loaded into the server, automatically disabling plugin.");
			pluginManager.disablePlugin(this);

			return;
		}
		// Initialize configurations.
		this.reloadConfigurations();

		// Initialize commands / events after because
		// some systems need the managers to be setup first.
		this.initCommands();
		this.initEvents();

//		List<String> drops = new ArrayList<>();
//
//		Zombie testZombie = new Zombie(
//				this,
//				"Zombie",
//				10,
//				ForagingMonsterAggression.AGGRESSIVE,
//				new ForagingMonsterStats(
//						5,
//						5
//				),
//				new ForagingMonsterEquipment(
//						new ItemStack(Material.AIR),
//						new ItemStack(Material.AIR),
//						new ItemStack(Material.AIR),
//						new ItemStack(Material.AIR),
//						new ItemStack(Material.AIR),
//						new ItemStack(Material.AIR)
//				),
//				drops
//		);
//		this.getLogger().info(testZombie.getName());
	}

	@Override
	public void onDisable() { }

	/**
	 * Initialize all commands.
	 */
	private void initCommands() {
		getCommand("giveitem").setExecutor(new GiveItemCommand(this, this.itemManager));
		getCommand("reloadconfigs").setExecutor(new ReloadConfigurationsCommand(this));
		getCommand("cool").setExecutor(new CoolCommand(this));
	}

	/**
	 * Initialize all events.
	 */
	private void initEvents() {
		pluginManager.registerEvents(new JoinEvent(this), this);
		pluginManager.registerEvents(new QuitEvent(this), this);
		pluginManager.registerEvents(new RegionEnterEvent(this), this);
		pluginManager.registerEvents(new MonsterDeathEvent(this), this);
	}

	/**
	 * Initialize all configurations custom configurations.
	 */
	private void initConfigs() {
		String[] configurations = {"config", "items", "monsters", "regions"};

		for(String configName : configurations) {
			File file = new File(getDataFolder(), String.format("%s.yml", configName));

			if(!file.exists()) {
				// For now I'm making it replace the config.
				this.saveResource(String.format("%s.yml", configName), true);
			}

			FileConfiguration configuration = new YamlConfiguration();
			try {
				configuration.load(file);
				this.configs.put(configName, configuration);
			} catch(IOException | InvalidConfigurationException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Reload all configurations.
	 */
	public void reloadConfigurations() {
		this.initConfigs();

		this.itemManager = new ItemManager(this);
		this.itemManager.initItems();

		this.regionManager = new RegionManager(this);

		this.langManager = new LangManager(this);
		this.langManager.initMessages();

		this.database = new Database(this);
		this.database.establishConnection();

		this.getLogger().info("Reloaded configurations!");
	}

	/**
	 * Get a specific configuration.
	 * @param name Configuration name.
	 * @return FileConfiguration.
	 */
	public FileConfiguration getConfiguration(String name) {
		// Just return main config.
		if(name.equalsIgnoreCase("config")) return getConfig();
		// Check to make sure the config is actually added.
		return configs.getOrDefault(name, null);
	}

	public LangManager getLangManager() {
		return langManager;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public RegionManager getRegionManager() {
		return regionManager;
	}

	public Database getDatabase() {
		return database;
	}
}
