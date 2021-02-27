package me.tech.foraging.monsters.events;

import com.destroystokyo.paper.event.entity.EntityPathfindEvent;
import me.tech.foraging.Foraging;
import me.tech.foraging.monsters.Monster;
import me.tech.foraging.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class MonsterDeathEvent implements Listener {
	private final Foraging foraging;

	public MonsterDeathEvent(Foraging foraging) {
		this.foraging = foraging;
	}

	@EventHandler
	public void onMonsterDeath(EntityDeathEvent ev) {
		Entity entity = ev.getEntity();
		ev.getDrops().removeAll(ev.getDrops());

		if(entity instanceof Player) return;
		// Is not a custom created entity.
		if(!this.foraging.getSpawnedMonsters().containsKey(entity.getUniqueId())) return;
		Player killer = ev.getEntity().getKiller();
		Monster monster = this.foraging.getSpawnedMonsters().get(entity.getUniqueId());
		this.foraging.getLogger().info("Killer is " + killer.getName());
		// TODO: 2/23/2021 IMPLEMENT 
//		for(String item : monster.getDrops()) {
//			this.foraging.getLogger().info(String.format("Dropped %s", item));
//		}
	}

	@EventHandler
	public void onMonsterDamage(EntityDamageByEntityEvent ev) {
		Entity entity = ev.getEntity();
		if(!this.foraging.getSpawnedMonsters().containsKey(entity.getUniqueId())) return;

		Monster monster = this.foraging.getSpawnedMonsters().get(entity.getUniqueId());
		this.foraging.getLogger().info("RETURNING ENTITY TO INITIAL LOCATION!!!!!!!!");
		monster.returnToInitialSpawnLocation();
	}

	@EventHandler
	public void onMonsterPathFind(EntityPathfindEvent ev) {
//		Entity entity = ev.getEntity();
//		if(!this.foraging.monsters.containsKey(entity.getUniqueId())) return;
//
//		Monster monster = this.foraging.monsters.get(entity.getUniqueId());
//		if(ev.getTargetEntity() != null && ev.getTargetEntity().getLocation() != monster.getSpawnLocation()) {
//			ev.setCancelled(true);
//			this.foraging.getLogger().info("Target is not spawn location.");
//		}
	}
}
