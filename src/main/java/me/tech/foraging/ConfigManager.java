package me.tech.foraging;

import me.tech.foraging.models.player.ForagingPlayerLanguage;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
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
	 * Main configuration loader.
	 */
	public void load() {
		String[] configurations = {"config.yml"};

		this.loadLanguages();

		for(String fileName : configurations) {
			// Current path of folders to create.
			List<String> currentPath = new ArrayList<>();

			// Load any and all directories a configuration file may have.
			if(fileName.contains("/")) {
				// List of all directories leading to the file.
				List<String> directoryTree = Arrays.asList(fileName.split("/"));;
				// Remove the actual config file from the directory list.
				directoryTree.remove(directoryTree.size() - 1);
				for(String directory : directoryTree) {
					// Add current directory to the path list.
					currentPath.add(directory);
					createFolder(String.join("/", currentPath));
				}
			}

			String path = String.join("/", currentPath);
			if(!path.equals("")) path += "/";

			File file = new File(foraging.getDataFolder(), String.format("%s%s", path, fileName));

			if(!file.exists()) foraging.saveResource(String.format("%s%s", path, fileName), false);
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
		File file = new File(foraging.getDataFolder(), path);
		if(!file.exists()) {
			file.mkdir();
			foraging.getLogger().info(String.format("Created directory %s", path));
		}
	}
}
