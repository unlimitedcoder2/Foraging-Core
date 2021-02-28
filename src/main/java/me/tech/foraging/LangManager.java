package me.tech.foraging;

import static me.tech.foraging.utils.ChatUtils.color;

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
		return this.messages.get(id);
	}

	public void load() {
		ForagingPlayerLanguage[] supportedLanguages = ForagingPlayerLanguage.values();
		this.messages.clear();

		for(ForagingPlayerLanguage language : supportedLanguages) {
			FileConfiguration configuration = foraging.getConfigManager().get(language.getId());

			for(String path : configuration.getKeys(true)) {
				if(!configuration.isSet(path)) continue;

				if(configuration.isString(path))
					this.messages.put(path, color(configuration.getString(path)));
				else if(configuration.isList(path))
					this.messages.put(
							path,
							color(String.join("\n", configuration.getStringList(path)))
					);
			}
		}
	}
}
