package me.tech.foraging;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Foraging extends JavaPlugin {
	private static Foraging instance;
	private final PluginManager pluginManager = this.getServer().getPluginManager();

	@Override
	public void onEnable() {
		instance = this;

		if(!this.checkForRequiredPlugins()) {
			getLogger().severe("Plugins required for Foraging Core to load are not installed on the server!");
			pluginManager.disablePlugin(this);
		}


	}

	@Override
	public void onDisable() { }

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

	public static Foraging getInstance() {
		return instance;
	}
}
