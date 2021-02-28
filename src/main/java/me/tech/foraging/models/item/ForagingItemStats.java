package me.tech.foraging.models.item;

import static me.tech.foraging.utils.ChatUtils.color;

public class ForagingItemStats {
	private final double health;
	private final double damage;
	private final double defense;
	private final double speed;
	private final double weight;
	private final int power;

	public ForagingItemStats(double health, double damage, double defense, double speed, double weight, int power) {
		this.health = health;
		this.damage = damage;
		this.defense = defense;
		this.speed = speed;
		this.weight = weight;
		this.power = power;
	}

	/**
	 * @return How much additional health the item gives.
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @return Health in a formatted string.
	 */
	public String getHealthFormatted() {
		String str = health > 0 ? "&7Health: &c+" : "&7Health: &c-";
		return color(str+health);
	}

	/**
	 * @return How much damage the item does.
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * @return Damage in a formatted string.
	 */
	public String getDamageFormatted() {
		String str = damage > 0 ? "&7Damage: &a+" : "&7Damage: &a-";
		return color(str+damage);
	}

	/**
	 * @return How much additional defense the item gives.
	 */
	public double getDefense() {
		return defense;
	}

	/**
	 * @return Defense in a formatted string.
	 */
	public String getDefenseFormatted() {
		String str = defense > 0 ? "&7Defense: &e+" : "&7Defense: &e-";
		return color(str+defense);
	}

	/**
	 * @return How much faster the player will be while using the item.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return Speed in a formatted string.
	 */
	public String getSpeedFormatted() {
		String str = speed > 0 ? "&7Speed: &b+" : "&7Speed: &b-";
		return color(str+speed);
	}

	/**
	 * @return How much weight that will be added onto the player.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @return Weight in a formatted string.
	 */
	public String getWeightFormatted() {
		String str = weight > 0 ? "&7Weight: &9+" : "&7Weight: &9-";
		return color(str+weight);
	}

	/**
	 * @return How strong the tool is.
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @return Power in a formatted string.
	 */
	public String getPowerFormatted() {
		String str = power > 0 ? "&7Power: &3+" : "&7Power: &3-";
		return color(str+power);
	}
}