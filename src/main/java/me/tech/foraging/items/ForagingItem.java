package me.tech.foraging.items;

import static me.tech.foraging.utils.ChatUtils.color;
import static me.tech.foraging.utils.ChatUtils.text;

import me.tech.foraging.models.item.ForagingItemModel;
import me.tech.foraging.models.item.ForagingItemRarity;
import me.tech.foraging.models.item.ForagingItemStats;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ForagingItem extends ForagingItemModel {
	public ForagingItem(String name, List<String> lore, ItemStack itemStack, boolean glowing, ForagingItemRarity rarity, ForagingItemStats stats) {
		super(name, lore, itemStack, glowing, rarity, stats);
	}

	/**
	 * @return ItemStack with all information associated with it.
	 */
	public ItemStack getItem() {
		ItemStack item = this.getItemStack();
		item = applyGlowing(item);

		List<String> lore = new ArrayList<>();
		applyLore().forEach(line -> lore.add(color(line)));
		applyStatsLore().forEach(line -> lore.add(color(line)));
		item.setLore(lore);

		String name = color(String.format("%s%s", getRarity().getColor(), getName()));

		ItemMeta meta = item.getItemMeta();
		meta.displayName(text(name));

		item.setItemMeta(meta);
		return item;
	}

	/**
	 * If the item is supposed to glow apply a glowing
	 * effect to it.
	 * @param item
	 * @return
	 */
	private ItemStack applyGlowing(ItemStack item) {
		if(!isGlowing()) return item;
		ItemMeta meta = item.getItemMeta();

		// TODO: 2/28/2021 Make enchantments invisible.
		if(item.getType() != Material.FISHING_ROD) meta.addEnchant(Enchantment.LURE, 1, true);
		else meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);

		item.setItemMeta(meta);
		return item;
	}

	/**
	 * If the item has any lore apply it.
	 * @return
	 */
	private List<String> applyLore() {
		List<String> lore = new ArrayList<>();
		lore.add(String.format("%s%s", getRarity().getBoldColor(), getRarity().getName()));

		if(getLore().size() != 0) {
			lore.add("");
			for(String line : getLore()) lore.add(line);
		}

		return lore;
	}

	/**
	 * If the tiem has any abilities apply the lore that goes
	 * along with it.
	 * @return
	 */
	// TODO: 2/28/2021 Item abilities aren't done yet.
	private List<String> applyAbilities() {
		List<String> lore = new ArrayList<>();
		return lore;
	}

	/**
	 * If the item has any stats associated with it
	 * give it the stats in the item's lore.
	 * @return
	 */
	private List<String> applyStatsLore() {
		List<String> lore = new ArrayList<>();
		lore.add("");

		// TODO: 2/27/2021 Possibly have a cleaner implementation of this in the future.
		if(getStats().getHealth() != 0) lore.add(getStats().getHealthFormatted());
		if(getStats().getDamage() != 0) lore.add(getStats().getDamageFormatted());
		if(getStats().getDefense() != 0) lore.add(getStats().getDefenseFormatted());
		if(getStats().getSpeed() != 0) lore.add(getStats().getSpeedFormatted());
		if(getStats().getWeight() != 0) lore.add(getStats().getWeightFormatted());
		if(getStats().getPower() != 0) lore.add(getStats().getPowerFormatted());

		// If the item has no stats then just
		// clear the lore.
		if(lore.size() == 1)
			lore.clear();

		return lore;
	}
}
