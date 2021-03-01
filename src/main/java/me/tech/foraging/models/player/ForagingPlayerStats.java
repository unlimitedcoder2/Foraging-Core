package me.tech.foraging.models.player;

public class ForagingPlayerStats {
	private int health;
	private int damage;
	private int defense;
	private int speed;
	private int weight;
	private int power;

	public ForagingPlayerStats(int health, int damage, int defense, int speed, int weight, int power) {
		this.health = health;
		this.damage = damage;
		this.defense = defense;
		this.speed = speed;
		this.weight = weight;
		this.power = power;
	}

	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
}
