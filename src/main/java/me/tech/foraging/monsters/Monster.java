package me.tech.foraging.monsters;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.ForagingMonster;
import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterEquipment;
import me.tech.foraging.models.monsters.ForagingMonsterStats;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

public class Monster extends ForagingMonster {
	private final Foraging foraging;
	private Location spawnLocation;

	public Monster(Foraging foraging, String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats, ForagingMonsterEquipment equipment, List<String> drops) {
		super(name, level, aggression, stats, equipment, drops);
		this.foraging = foraging;
	}

	/**
	 * Searches for players in a certain distance from the monster.
	 * @param range Maximum allowed value 128.
	 * @return Target player.
	 */
	public Player searchForPlayers(int range) {
		if(range > 128) return null;
		if(this.getAggression() != ForagingMonsterAggression.AGGRESSIVE) return null;
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

		this.foraging.monsters.put(this.entity.getUniqueId(), this);
		return true;
	}
}
