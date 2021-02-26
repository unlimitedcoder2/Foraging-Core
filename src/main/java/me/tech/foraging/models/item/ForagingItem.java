package me.tech.foraging.models.item;

import static me.tech.foraging.utils.ChatUtils.color;
import static me.tech.foraging.utils.ChatUtils.text;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ForagingItem {
	private final String name;
	private final List<String> lore;
	private final ItemStack itemStack;
	private boolean glowing = false;
	private final ForagingItemRarity rarity;
	private final ForagingItemStats stats;

	public ForagingItem(String name, List<String> lore, ItemStack itemStack, boolean glowing, ForagingItemRarity rarity, ForagingItemStats stats) {
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

	/**
	 * @return Item with correct formatting.
	 */
	public ItemStack getItem() {
		ItemStack itemStack = new ItemStack(this.itemStack);

		String itemName = color(String.format("%s%s", this.rarity.getColor(), this.name));

		ItemMeta itemMeta = itemStack.getItemMeta();

		// Add random enchant if the item is marked as glowing.
		if(this.glowing) {
			if(this.itemStack.getType() != Material.FISHING_ROD) itemMeta.addEnchant(Enchantment.LURE, 1, false);
			else itemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, false);
		}
		itemMeta.displayName(text(itemName));
		itemStack.setItemMeta(itemMeta);
		// Gotta set lore after because setItemMeta
		// is a bit weird.
		itemStack.setLore(this.formatLore());

		return itemStack;
	}

	/**
	 * @return Formatted item lore.
	 */
	private List<String> formatLore() {
		List<String> itemLore = new ArrayList<>();
		if(this.lore.size() != 0) {
			for(String line : this.lore) {
				itemLore.add(color(line));
			}
			itemLore.add("");
		}
		itemLore.add(color(String.format("%s%s", this.rarity.getBoldColor(), this.rarity.getName())));
		return itemLore;
	}
}

