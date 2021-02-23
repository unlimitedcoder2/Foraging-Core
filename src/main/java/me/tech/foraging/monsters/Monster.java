package me.tech.foraging.monsters;

import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterStats;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

class MonsterEquipment {
	ItemStack helmet;
	ItemStack chestplate;
	ItemStack leggings;
	ItemStack boots;
}

public class Monster {
	private final String name;
	private final int level;
	public Entity entity;
	private final ForagingMonsterAggression aggression;
	private final ForagingMonsterStats stats;
	private MonsterEquipment equipment;
	private Location spawnLocation;

	public Monster(String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats) {
		this.name = name;
		this.level = level;
		this.aggression = aggression;
		this.stats = stats;
	}

	/**
	 * Searches for players in a certain distance from the monster.
	 * @param range Maximum allowed value 128.
	 * @return Target player.
	 */
	public Player searchForPlayers(int range) {
		if(range > 128) return null;
		if(this.aggression != ForagingMonsterAggression.AGGRESSIVE) return null;
		if(this.entity == null) return null;

		for(Entity e : this.entity.getNearbyEntities(range, 3, range)) {
			if(!(e instanceof Player)) continue;
			return (Player) e;
		}

		return null;
	}

	/**
	 * (Prototype) Summons monster with hologram.
 	 * @param loc Location where to spawn the monster.
	 * @param entityType Type of entity to spawn.
	 * @return Whether it was successful or not.
	 */
	public boolean spawnMonster(Location loc, EntityType entityType) {
		if(this.entity == null) return false;
		if(loc.getWorld() == null) return false;
		this.entity = loc.getWorld().spawnEntity(loc, entityType);

		return true;
	}

	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public ForagingMonsterAggression getAggression() {
		return this.aggression;
	}

	public ForagingMonsterStats getStats() {
		return this.stats;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
