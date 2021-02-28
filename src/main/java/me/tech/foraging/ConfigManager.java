package me.tech.foraging;

import me.tech.foraging.models.player.ForagingPlayerLanguage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {
	private final Foraging foraging;
	private final HashMap<String, FileConfiguration> configurations = new HashMap<>();

	public ConfigManager(Foraging foraging) {
		this.foraging = foraging;
	}

	/**
	 * Get specific configuration.
	 * @param id
	 * @return
	 */
	public FileConfiguration get(String id) {
		if(id.equalsIgnoreCase("config")) return foraging.getConfig();
		return this.configurations.get(id);
	}

	/**
	 * Main configuration loader.
	 */
	public void load() {
		String[] configurations = {"config.yml"};

		this.loadLanguages();

		for(String config : configurations) {
			List<String> currentPath = new ArrayList<>();

			for(String directory : config.split("/")) {
				currentPath.add(directory);
				createFolder(String.join("/", currentPath));
			}

			File file = new File(foraging.getDataFolder(), config);
			// Create file if it doesn't exist.
			if(!file.exists()) foraging.saveResource(config, false);
			// ID of the configuration.
			String id = currentPath.get(currentPath.size() - 1);

			FileConfiguration configurationFile = new YamlConfiguration();
			try {
				configurationFile.load(file);
				this.configurations.put(
			        id.replaceAll(".yml", "")
						.replaceAll(".yaml", ""),
					configurationFile
				);
			} catch(IOException | InvalidConfigurationException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Load all language files.
	 */
	protected void loadLanguages() {
        createFolder("lang");
        ForagingPlayerLanguage[] supportedLanguages = ForagingPlayerLanguage.values();

        for(ForagingPlayerLanguage language : supportedLanguages) {
        	File file = new File(foraging.getDataFolder(), String.format("lang/%s.yml", language.getId()));

        	if(!file.exists())
        		foraging.saveResource(String.format("lang/%s.yml", language.getId()), false);
        }
	}

	/**
	 * Create a directory in the data folder.
	 * @param path
	 */
	protected void createFolder(String path) {
		// I'm really lazy so I'm just gonna add a check
		// to make sure the path doesn't have an extension.
		if(path.contains(".")) return;

		File file = new File(foraging.getDataFolder(), path);
		if(!file.exists()) {
			file.mkdir();
			foraging.getLogger().info(String.format("Created directory %s", path));
		}
	}
}
