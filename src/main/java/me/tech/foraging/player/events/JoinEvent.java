package me.tech.foraging.player.events;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.player.ForagingPlayer;
import me.tech.foraging.models.player.ForagingPlayerStats;
import me.tech.foraging.player.SummonManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
	private Foraging foraging;

	public JoinEvent(Foraging foraging) {
		this.foraging = foraging;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		// Imaginary test data.
		this.foraging.players.put(
			e.getPlayer().getUniqueId(),
			new ForagingPlayer(
				e.getPlayer().getUniqueId(),
				new ForagingPlayerStats(20, 5),
				new SummonManager(e.getPlayer())
			)
		);

		ForagingPlayer player = this.foraging.players.get(e.getPlayer().getUniqueId());
		player.getSummonManager();
	}
}
