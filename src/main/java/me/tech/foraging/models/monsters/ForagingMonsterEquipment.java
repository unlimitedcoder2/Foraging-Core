package me.tech.foraging.models.monsters;

import org.bukkit.inventory.ItemStack;

public class ForagingMonsterEquipment {
	private final ItemStack helmet;
	private final ItemStack chestplate;
	private final ItemStack leggings;
	private final ItemStack boots;
	private final ItemStack mainHand;
	private final ItemStack offHand;

	public ForagingMonsterEquipment(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack mainHand, ItemStack offHand) {
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
		this.mainHand = mainHand;
		this.offHand = offHand;
	}

	public ItemStack getHelmet() {
		return helmet;
	}

	public ItemStack getChestplate() {
		return chestplate;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public ItemStack getBoots() {
		return boots;
	}

	public ItemStack getMainHand() {
		return mainHand;
	}

	public ItemStack getOffHand() {
		return offHand;
	}
}
