package me.tech.foraging.models.item;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class ForagingItemModel {
	private final String name;
	private final List<String> lore;
	private final ItemStack itemStack;
	private boolean glowing = false;
	private final ForagingItemRarity rarity;
	private final ForagingItemStats stats;

	public ForagingItemModel(String name, List<String> lore, ItemStack itemStack, boolean glowing, ForagingItemRarity rarity, ForagingItemStats stats) {
		this.name = name;
		this.lore = lore;
		this.itemStack = itemStack;
		this.glowing = glowing;
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

	public boolean isGlowing() {
		return glowing;
	}

	public ForagingItemRarity getRarity() {
		return rarity;
	}

	public ForagingItemStats getStats() {
		return stats;
	}
}
