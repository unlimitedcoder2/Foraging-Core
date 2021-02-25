package me.tech.foraging.models.player;

public class ForagingPlayerStats {
	private double health = 20;
	private double damage = 0;

	public ForagingPlayerStats(double health, double damage) {
		this.health = health;
		this.damage = damage;
	}

	public double getHealth() {
		return health;
	}

	public double getDamage() {
		return damage;
	}
}
