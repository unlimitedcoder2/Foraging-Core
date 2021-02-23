package me.tech.foraging.models.monsters;

import org.bukkit.entity.Entity;

public class ForagingMonster {
	private String name;
	private int level;
	public Entity entity;
	private ForagingMonsterAggression aggression;
	private ForagingMonsterStats stats;
	private ForagingMonsterEquipment equipment;

	public ForagingMonster(String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats, ForagingMonsterEquipment equipment) {
		this.name = name;
		this.level = level;
		this.aggression = aggression;
		this.stats = stats;
		this.equipment = equipment;
	}

	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public ForagingMonsterAggression getAggression() {
		return this.aggression;
	}

	public ForagingMonsterStats getStats() {
		return this.stats;
	}

	public ForagingMonsterEquipment getEquipment() {
		return this.equipment;
	}
}
