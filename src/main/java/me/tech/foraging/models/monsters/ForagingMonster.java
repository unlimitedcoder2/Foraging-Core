package me.tech.foraging.models.monsters;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import org.bukkit.entity.Entity;

import java.util.List;

public class ForagingMonster {
	private final String name;
	private final int level;
	private Entity entity;
	private Hologram hologram;
	private final ForagingMonsterAggression aggression;
	private final ForagingMonsterStats stats;
	private final ForagingMonsterEquipment equipment;
	private final List<String> drops;

	private boolean returningToLocation = false;

	public ForagingMonster(String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats, ForagingMonsterEquipment equipment, List<String> drops) {
		this.name = name;
		this.level = level;
		this.aggression = aggression;
		this.stats = stats;
		this.equipment = equipment;
		this.drops = drops;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Hologram getHologram() {
		return hologram;
	}

	public void setHologram(Hologram hologram) {
		this.hologram = hologram;
	}

	public ForagingMonsterAggression getAggression() {
		return aggression;
	}

	public ForagingMonsterStats getStats() {
		return stats;
	}

	public ForagingMonsterEquipment getEquipment() {
		return equipment;
	}

	public List<String> getDrops() {
		return drops;
	}

	public boolean isReturningToLocation() {
		return returningToLocation;
	}

	public void setReturningToLocation(boolean returningToLocation) {
		this.returningToLocation = returningToLocation;
	}
}