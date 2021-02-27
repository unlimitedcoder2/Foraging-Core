package me.tech.foraging.monsters;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.*;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Monster extends ForagingMonster {
	private final Foraging foraging;
	private Location spawnLocation;

	public Monster(Foraging foraging, String name, int level, EntityType entityType, ForagingMonsterAggression aggression, ForagingMonsterStats stats, ForagingMonsterEquipment equipment, List<ForagingMonsterDrops> drops) {
		super(name, level, entityType, aggression, stats, equipment, drops);
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
		if(this.getEntity() == null) return null;

		for(Entity e : this.getEntity().getNearbyEntities(range, 3, range)) {
			if(!(e instanceof Player)) continue;
			return (Player) e;
		}
		return null;
	}

	public void targetNearbyPlayers(int range) {
		 Player target = this.searchForPlayers(range);
		 if(target == null || this.getEntity() == null) return;

		LivingEntity livingEntity = (LivingEntity) this.getEntity();
		livingEntity.attack(target);
	}

	/**
	 * Make the monster drop whatever they're doing to go back to
	 * where they originally spawned.
	 */
	public void returnToInitialSpawnLocation() {
		if(this.getEntity() == null
			|| !this.foraging.getSpawnedMonsters().containsKey(this.getEntity().getUniqueId())
			/* Entity is already returning to location. */
			|| this.isReturningToLocation()
		) return;

		this.walkToLocation(spawnLocation, 2);

		// Every second check to see if the entity
		// has made it back to their spawn location.
		new BukkitRunnable() {
			private Monster monster = foraging.getSpawnedMonsters().get(getEntity().getUniqueId());

			@Override
			public void run() {
				// Monster is returning to their spawn location
				// and distance between them and spawn location < 5.
				if(monster.isReturningToLocation() && monster.getEntity().getLocation().distance(monster.getSpawnLocation()) < 5) {
					monster.setReturningToLocation(false);
					cancel();
				}
			}
		}.runTaskTimer(foraging, 20, 20);
	}

	private void walkToLocation(Location loc, double speed) {
		LivingEntity livingEntity = (LivingEntity) this.getEntity();
		// NMS magic.
		((EntityInsentient) ((CraftEntity) livingEntity).getHandle())
			.getNavigation()
			.a(loc.getX(), loc.getY(), loc.getZ(), speed);
	}

	/**
	 * (Prototype) Summons monster with hologram.
 	 * @param loc Location where to spawn the monster.
	 * @return Whether it was successful or not.
	 */
	public boolean spawnMonster(Location loc) {
		if(loc.getWorld() == null) return false;
		this.setEntity(loc.getWorld().spawnEntity(loc, this.getEntityType()));

		this.spawnLocation = loc;
		this.foraging.getSpawnedMonsters().put(this.getEntity().getUniqueId(), this);
		return true;
	}

	/**
	 * @return Initial monster spawn location.
	 */
	public Location getSpawnLocation() {
		return spawnLocation;
	}
}
