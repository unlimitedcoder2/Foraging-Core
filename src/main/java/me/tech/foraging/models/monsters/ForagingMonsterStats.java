package me.tech.foraging.models.monsters;

public class ForagingMonsterStats {
	private double health;
	private double damage;

	public ForagingMonsterStats(double health, double damage) {
		this.health = health;
		this.damage = damage;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
}
