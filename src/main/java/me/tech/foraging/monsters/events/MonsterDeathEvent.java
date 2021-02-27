package me.tech.foraging.monsters.events;

import com.destroystokyo.paper.event.entity.EntityPathfindEvent;
import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.ForagingMonsterDrops;
import me.tech.foraging.monsters.Monster;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

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
		if(!this.foraging.getSpawnedMonsters().containsKey(entity.getUniqueId())) return;
		Monster monster = this.foraging.getSpawnedMonsters().get(entity.getUniqueId());

		// Was killed by a player.
	    if(ev.getEntity().getKiller() == null) {
		 	Player killer = ev.getEntity().getKiller();
			return;
	    }

	    for(ForagingMonsterDrops drop : monster.getDrops()) {
		    ItemStack item = this.foraging.getItemManager().getItems().get(drop.getID()).getItem();
	        ev.getDrops().add(item);
	    }
	}

	@EventHandler
	public void onMonsterDamage(EntityDamageByEntityEvent ev) {
		Entity entity = ev.getEntity();
		if(!this.foraging.getSpawnedMonsters().containsKey(entity.getUniqueId())) return;

		Monster monster = this.foraging.getSpawnedMonsters().get(entity.getUniqueId());
		this.foraging.getLogger().info("RETURNING ENTITY TO INITIAL LOCATION!!!!!!!!");
		monster.returnToInitialSpawnLocation(1.25);
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
