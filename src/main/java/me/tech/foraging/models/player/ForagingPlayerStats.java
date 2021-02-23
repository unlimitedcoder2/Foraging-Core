package me.tech.foraging.models.player;

public class ForagingPlayerStats {
	private float health = 20;
	private float damage = 0;

	public ForagingPlayerStats(float health, float damage) {
		this.health = health;
		this.damage = damage;
	}

	public float getHealth() {
		return this.health;
	}

	public float getDamage() {
		return this.damage;
	}
}
