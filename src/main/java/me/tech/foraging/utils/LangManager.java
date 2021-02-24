package me.tech.foraging.utils;

import static me.tech.foraging.utils.ChatUtils.color;

import me.tech.foraging.Foraging;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class LangManager {
	private final Foraging foraging;

	private final HashMap<String, String> messages = new HashMap<>();

	public LangManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public void initMessages() {
		try {
			ConfigurationSection lang = this.foraging.getConfiguration("config").getConfigurationSection("lang");
			this.messages.clear();

			for (String path : lang.getKeys(true)) {
				String str;
				if(lang.isString(path)) str = lang.getString(path);
				// :shrug:
				else if(lang.isList(path)) str = String.join("\n", (CharSequence) lang.getList(path));
				else continue;

				this.messages.put(
					path,
					/* Automatically color it. */
					color(str)
				);
			}
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}

	/** @return Message. */
	public String getMSG(String id) {
		return this.messages.get(id);
	}

	public HashMap<String, String> getMessages() {
		return this.messages;
	}
}
