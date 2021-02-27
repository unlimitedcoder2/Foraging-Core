package me.tech.foraging.models.item;

public class ForagingItemStats {
	private final double health;
	private final double damage;
	private final double speed;
	private final double weight;
	private final int power;

	public ForagingItemStats(double health, double damage, double speed, double weight, int power) {
		this.health = health;
		this.damage = damage;
		this.speed = speed;
		this.weight = weight;
		this.power = power;
	}

	/**
	 * How much additional health the item gives.
	 * @return
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * How much damage the item does.
	 * @return
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * How much faster the player will be while using the item.
	 * @return
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * How much weight that will be added onto the player.
	 * @return
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * How strong the tool is.
	 * @return
	 */
	public int getPower() {
		return power;
	}
}