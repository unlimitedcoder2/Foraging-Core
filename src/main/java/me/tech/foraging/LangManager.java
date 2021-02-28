package me.tech.foraging;

import me.tech.foraging.models.player.ForagingPlayerLanguage;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class LangManager {
	private final Foraging foraging;

	private final HashMap<String, String> messages = new HashMap<>();

	public LangManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public String get(String id) {
		return null;
	}

	// TODO: 2/28/2021 Implement this.
	public void load() {
		ForagingPlayerLanguage[] supportedLanguages = ForagingPlayerLanguage.values();
		this.messages.clear();

		for(ForagingPlayerLanguage language : supportedLanguages) {
			FileConfiguration configuration = foraging.getConfigManager().get(language.getId());

			for(String path : configuration.getKeys(true)) {
				foraging.getLogger().info(path);
			}
		}
	}
}
