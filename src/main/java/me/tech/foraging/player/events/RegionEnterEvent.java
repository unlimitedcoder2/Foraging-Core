package me.tech.foraging.player.events;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.tech.foraging.Foraging;
import net.raidstone.wgevents.events.RegionsChangedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

public class RegionEnterEvent implements Listener {
	private final Foraging foraging;

	public RegionEnterEvent(Foraging foraging) {
		this.foraging = foraging;
	}

	@EventHandler
	public void onRegionEnter(RegionsChangedEvent ev) {
		Set<String> regions = ev.getCurrentRegionsNames();

	}
}
