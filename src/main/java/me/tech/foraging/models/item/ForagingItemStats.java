package me.tech.foraging.models.item;

public class ForagingItemStats {
	private float health = 0;
	private float damage = 0;

	public ForagingItemStats(float health, float damage) {
		this.health = health;
		this.damage = damage;
	}

	public float getHealth() { return this.health; }
	public float getDamage() { return this.damage; }
}
