package me.tech.foraging.models.item;

import java.util.List;

public class ForagingItem {
	private final String name;
	private final List<String> lore;
	private final ForagingItemRarity rarity;
	private final ForagingItemStats stats;

	public ForagingItem(String name, List<String> lore, ForagingItemRarity rarity, ForagingItemStats stats) {
		this.name = name;
		this.lore = lore;
		this.rarity = rarity;
		this.stats = stats;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getLore() {
		return this.lore;
	}

	public ForagingItemRarity getRarity() {
		return this.rarity;
	}

	public ForagingItemStats getStats() {
		return this.stats;
	}
}

