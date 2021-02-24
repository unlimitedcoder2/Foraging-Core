package me.tech.foraging.items;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.item.ForagingItem;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ItemManager {
	private final Foraging foraging;

	public HashMap<String, ForagingItem> items = new HashMap<>();

	public ItemManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public void initItems() {
		FileConfiguration itemsConfig = this.foraging.getConfiguration("items");
//		this.foraging.getLogger().info("Deep search.");
//		this.foraging.getLogger().info(itemsConfig.getKeys(true).toString());
//		this.foraging.getLogger().info("Not deep search.");
//		this.foraging.getLogger().info(itemsConfig.getKeys(false).toString());
		this.foraging.getLogger().info("doin key thingoay");
		for(String item : itemsConfig.getConfigurationSection("items").getKeys(false)) {
			this.foraging.getLogger().info(item);
		}
	}
}

/*
items:
  stick:
    name: 'Cool Stick'
    lore:
      - 'Test'
      - 'XD'
    itemstack: 'STICK'
    rarity: 'COMMON'
    stats:
      damage: 5
 */