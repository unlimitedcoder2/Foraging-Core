package me.tech.foraging.models.player;

public class ForagingPlayerStats {
	private double health;
	private double damage;
	private double defense;
	private double speed;
	private double weight;
	private int power;

	public ForagingPlayerStats(double health, double damage, double defense, double speed, double weight, int power) {
		this.health = health;
		this.damage = damage;
		this.defense = defense;
		this.speed = speed;
		this.weight = weight;
		this.power = power;
	}

	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getDefense() {
		return defense;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}

	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
}
