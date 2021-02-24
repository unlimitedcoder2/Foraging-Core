package me.tech.foraging.player.events;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.player.ForagingPlayer;
import me.tech.foraging.models.player.ForagingPlayerStats;
import me.tech.foraging.player.SummonManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
	private final Foraging foraging;

	public JoinEvent(Foraging foraging) {
		this.foraging = foraging;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		// Imaginary test data.
		this.foraging.players.put(
			ev.getPlayer().getUniqueId(),
			new ForagingPlayer(
				new ForagingPlayerStats(20, 5),
				new SummonManager(this.foraging, ev.getPlayer())
			)
		);

		ForagingPlayer foragingPlayer = this.foraging.players.get(ev.getPlayer().getUniqueId());
		// extremely hot.
		foragingPlayer.getSummonManager().setForagingPlayer(foragingPlayer);
		foragingPlayer.getSummonManager().spawnMobs();
	}
}
