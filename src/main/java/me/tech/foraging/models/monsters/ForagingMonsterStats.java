package me.tech.foraging.models.monsters;

public class ForagingMonsterStats {
	private float health;
	private float damage;

	public ForagingMonsterStats(float health, float damage) {
		this.health = health;
		this.damage = damage;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}
}
