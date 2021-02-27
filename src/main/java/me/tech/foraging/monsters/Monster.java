package me.tech.foraging.monsters;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.ForagingMonster;
import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterEquipment;
import me.tech.foraging.models.monsters.ForagingMonsterStats;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public abstract class Monster extends ForagingMonster {
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
		if(this.getEntity() == null && !this.foraging.monsters.containsKey(this.getEntity().getUniqueId()) && this.isReturningToLocation()) return;

		LivingEntity livingEntity = (LivingEntity) this.getEntity();

		((EntityInsentient) ((CraftEntity) livingEntity).getHandle())
			.getNavigation()
			.a(this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ(), 1.5);
		this.setReturningToLocation(true);

		new BukkitRunnable() {
			private Monster monster = foraging.monsters.get(getEntity().getUniqueId());

			@Override
			public void run() {
				if(monster.isReturningToLocation()) {
					if(getEntity().getLocation().distance(monster.getSpawnLocation()) < 5) {
						monster.setReturningToLocation(false);
						cancel();
					}
				}
			}
		}.runTaskTimer(this.foraging, 20*1, 20*1);
	}

	/**
	 * (Prototype) Summons monster with hologram.
 	 * @param loc Location where to spawn the monster.
	 * @param entityType Type of entity to spawn.
	 * @return Whether it was successful or not.
	 */
	public boolean spawnMonster(Location loc, EntityType entityType) {
		if(loc.getWorld() == null) return false;
		this.setEntity(loc.getWorld().spawnEntity(loc, entityType));

		this.spawnLocation = loc;
		this.foraging.monsters.put(this.getEntity().getUniqueId(), this);
		return true;
	}

	/**
	 * @return Initial monster spawn location.
	 */
	public Location getSpawnLocation() {
		return spawnLocation;
	}
}
