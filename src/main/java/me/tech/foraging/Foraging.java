package me.tech.foraging;

import me.tech.foraging.commands.SetLanguageCommand;
import me.tech.foraging.database.Database;
import me.tech.foraging.items.ItemManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Foraging extends JavaPlugin {
	private final PluginManager pluginManager = this.getServer().getPluginManager();

	private ItemManager itemManager;
	private ConfigManager configManager;
	private LangManager langManager;
	private Database database;

	@Override
	public void onEnable() {
		if(!this.checkForRequiredPlugins()) {
			getLogger().severe("Plugins required for Foraging Core to load are not installed on the server!");
			pluginManager.disablePlugin(this);
		}

		this.initCommands();
		this.initEvents();
		this.reloadManagers();

		getLogger().info("All systems have been loaded.");
	}

	@Override
	public void onDisable() { }

	/**
	 * Initialize server commands.
	 */
	private void initCommands() {
		getCommand("setlanguage").setExecutor(new SetLanguageCommand(this));
	}

	/**
	 * Initialize server events.
	 */
	private void initEvents() { }

	/**
	 * Reload all system managers.
	 */
	private void reloadManagers() {
		this.itemManager = new ItemManager(this);
		this.itemManager.load();

		this.configManager = new ConfigManager(this);
		this.configManager.load();

		this.langManager = new LangManager(this);
		this.langManager.load();

		this.database = new Database(this);
		this.database.establishConnection();

		getLogger().info("Reloaded system managers.");
	}

	/**
	 * Make sure the plugins required for the plugin to function properly
	 * are loaded into the server.
	 * @return
	 */
	protected boolean checkForRequiredPlugins() {
		return (
			pluginManager.isPluginEnabled("HolographicDisplays") &&
			pluginManager.isPluginEnabled("WorldGuard") &&
			pluginManager.isPluginEnabled("WorldGuardEvents")
		);
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public LangManager getLangManager() {
		return langManager;
	}

	public Database getDatabase() {
		return database;
	}
}
