package me.tech.foraging.monsters.events;

import me.tech.foraging.Foraging;
import me.tech.foraging.monsters.Monster;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
		if(!this.foraging.monsters.containsKey(entity.getUniqueId())) return;
		Player killer = ev.getEntity().getKiller();
		Monster monster = this.foraging.monsters.get(entity.getUniqueId());
		// TODO: 2/23/2021 IMPLEMENT 
		for(String item : monster.getDrops()) {
		}
	}
}
