package me.tech.foraging;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;

public class ConfigManager {
	private final Foraging foraging;

	private HashMap<String, FileConfiguration> configurations = new HashMap<>();

	public ConfigManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public void initConfigs() {
		File directory = new File(foraging.getDataFolder(), "");
 		String[] contents = directory.list();

 		for(String content : contents) {
 			foraging.getLogger().info(content);
	    }

 		this.configurations.clear();
	}
}
