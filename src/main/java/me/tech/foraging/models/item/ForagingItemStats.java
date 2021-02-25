package me.tech.foraging.models.item;

public class ForagingItemStats {
	private double health = 0;
	private double damage = 0;

	public ForagingItemStats(double health, double damage) {
		this.health = health;
		this.damage = damage;
	}

	public double getHealth() { return health; }
	public double getDamage() { return damage; }
}
