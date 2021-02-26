package me.tech.foraging.models.item;

import static me.tech.foraging.utils.ChatUtils.color;
import static me.tech.foraging.utils.ChatUtils.text;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ForagingItem {
	private final String name;
	private final List<String> lore;
	private final ItemStack itemStack;
	private final ForagingItemRarity rarity;
	private final ForagingItemStats stats;

	public ForagingItem(String name, List<String> lore, ItemStack itemStack, ForagingItemRarity rarity, ForagingItemStats stats) {
		this.name = name;
		this.lore = lore;
		this.itemStack = itemStack;
		this.rarity = rarity;
		this.stats = stats;
	}

	public String getName() {
		return name;
	}

	public List<String> getLore() {
		return lore;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public ForagingItemRarity getRarity() {
		return rarity;
	}

	public ForagingItemStats getStats() {
		return stats;
	}

	/**
	 * @return Item with correct formatting.
	 */
	public ItemStack getItem() {
		ItemStack item = new ItemStack(this.itemStack);

		String name = color(String.format("%s%s", this.rarity.getColor(), this.name));
		List<String> lore = new ArrayList<>();
		if(this.lore.size() != 0) {
			for(String l : this.lore) {
				lore.add(color(l));
			}
			lore.add("");
		}
		lore.add(color(String.format("%s%s", this.rarity.getBoldColor(), this.rarity.getName().toUpperCase())));

		// wtf am i doing?
		ItemMeta meta = item.getItemMeta();
		meta.displayName(text(name));

		item.setItemMeta(meta);
		item.setLore(lore);


		return item;
	}
}

