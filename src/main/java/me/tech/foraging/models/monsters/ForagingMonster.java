package me.tech.foraging.models.monsters;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import me.tech.foraging.models.item.ForagingItem;
import org.bukkit.entity.Entity;

public class ForagingMonster {
	private final String name;
	private final int level;
	public Entity entity;
	// Hologram above entity.
	private Hologram hologram;
	private final ForagingMonsterAggression aggression;
	private final ForagingMonsterStats stats;
	private final ForagingMonsterEquipment equipment;
	private final ForagingItem[] drops;

	public ForagingMonster(String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats, ForagingMonsterEquipment equipment, ForagingItem[] drops) {
		this.name = name;
		this.level = level;
		this.aggression = aggression;
		this.stats = stats;
		this.equipment = equipment;
		this.drops = drops;
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

	public Hologram getHologram() {
		return this.hologram;
	}

	public void setHologram(Hologram hologram) {
		this.hologram = hologram;
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

	public ForagingItem[] getDrops() {
		return this.drops;
	}
}
