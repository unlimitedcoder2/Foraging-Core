package me.tech.foraging.items;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.item.ForagingItem;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ItemManager {
	private final Foraging foraging;
	private FileConfiguration itemsConfig;

	public HashMap<String, ForagingItem> items = new HashMap<>();

	public ItemManager(Foraging foraging) {
		this.foraging = foraging;
		this.itemsConfig = this.foraging.getConfiguration("items");
	}

	public void initItems() {
		for(String item : this.itemsConfig.getStringList("items")) {

		}
	}
}
