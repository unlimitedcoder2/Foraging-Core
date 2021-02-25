package me.tech.foraging.items;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.item.ForagingItem;
import me.tech.foraging.models.item.ForagingItemRarity;
import me.tech.foraging.models.item.ForagingItemStats;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemManager {
	private final Foraging foraging;

	private final HashMap<String, ForagingItem> items = new HashMap<>();

	public ItemManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public void initItems() {
		FileConfiguration itemsConfig = this.foraging.getConfiguration("items");
		this.items.clear();

		for(String id : itemsConfig.getConfigurationSection("items").getKeys(false)) {
			ConfigurationSection item = itemsConfig.getConfigurationSection(String.format("items.%s", id));

			ForagingItem foragingItem = new ForagingItem(
				item.getString("name"),
				item.getStringList("lore"),
				new ItemStack(Material.getMaterial(item.getString("itemstack"))),
				/* For now every item is common. */
				ForagingItemRarity.valueOf(item.getString("rarity").toUpperCase()),
				new ForagingItemStats(
					item.getDouble("stats.health"),
					item.getDouble("stats.damage"),
					item.getDouble("stats.weight")
				)
			);

			items.put(id.toLowerCase(), foragingItem);
		}
	}

	public HashMap<String, ForagingItem> getItems() {
		return items;
	}
}