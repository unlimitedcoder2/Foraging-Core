package me.tech.foraging.models.item;

public class ForagingItemStats {
	private double baseHealth = 0;
	private double baseDamage = 0;
	private double baseWeight = 0;

	public ForagingItemStats(double baseHealth, double baseDamage, double baseWeight) {
		this.baseHealth = baseHealth;
		this.baseDamage = baseDamage;
		this.baseWeight = baseWeight;
	}

	public double getBaseHealth() {
		return baseHealth;
	}

	public double getBaseDamage() {
		return baseDamage;
	}

	public double getBaseWeight() {
		return baseWeight;
	}
}
