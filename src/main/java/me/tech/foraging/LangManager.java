package me.tech.foraging;

import static me.tech.foraging.utils.ChatUtils.color;

import me.tech.foraging.models.player.ForagingPlayerLanguage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class LangManager {
	private final Foraging foraging;

	private final HashMap<String, String> messages = new HashMap<>();
	private final HashMap<String, ForagingPlayerLanguage> supportedLanguages = new HashMap<>();

	public LangManager(Foraging foraging) {
		this.foraging = foraging;
	}

	/**
	 * Get a message.
	 * @param id Message ID.
	 * @param language Player's selected language.
	 * @return Colored message.
	 */
	public String get(String id, ForagingPlayerLanguage language) {
		return this.messages.get(language.getId()+"."+id);
	}

	/**
	 * Get a message.
	 * @param id Message ID.
	 * @return Colored message.
	 */
	public String get(String id) {
		return this.messages.get(id);
	}

	/**
	 * Load all language files.
	 */
	public void load() {
		FileConfiguration languages = this.foraging.getConfigManager().get("languages");
		// Just wipe everything.
		this.messages.clear();
		this.supportedLanguages.clear();

		for(String language : languages.getKeys(false)) {
			foraging.getLogger().info(String.format("Testing language %s", language));
			FileConfiguration languageConfiguration = foraging.getConfigManager().get(language);
			// Load all messages in the lang file.
			for(String path : languageConfiguration.getKeys(true)) {
				if(!languageConfiguration.isSet(path)) continue;
				// If it's just a string put it in the hashmap.
				if(languageConfiguration.isString(path)) {
					this.messages.put(
							String.format("%s.%s", language, path),
							color(languageConfiguration.getString(path))
					);
				// If it's a list join it with a newline.
				} else if(languageConfiguration.isList(path)) {
					this.messages.put(
							String.format("%s.%s", language, path),
							color(String.join("\n", languageConfiguration.getStringList(path)))
					);
				}
			}
			// Information about language in languages.yml
			ConfigurationSection languageInfo = languages.getConfigurationSection(language);
			// Add the language to the list of supported languages.
			this.supportedLanguages.put(
					language,
					new ForagingPlayerLanguage(
							language,
							languageInfo.getString("name"),
							languageInfo.getBoolean("enabled"),
							languageInfo.getBoolean("hidden")
					)
			);
		}
	}

	public HashMap<String, ForagingPlayerLanguage> getSupportedLanguages() {
		return supportedLanguages;
	}
}
