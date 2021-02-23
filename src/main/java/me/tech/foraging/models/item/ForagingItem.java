package me.tech.foraging.models.item;

import static me.tech.foraging.utils.ChatUtils.color;
import org.bukkit.inventory.ItemStack;

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
		return this.name;
	}

	public List<String> getLore() {
		return this.lore;
	}

	public ItemStack getItemStack() {
		return this.itemStack;
	}

	public ForagingItemRarity getRarity() {
		return this.rarity;
	}

	public ForagingItemStats getStats() {
		return this.stats;
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
		lore.add(color(String.format("%s%s", this.rarity.getColor(), this.rarity.getName().toUpperCase())));

		item.setLore(lore);
		return item;
	}
}

