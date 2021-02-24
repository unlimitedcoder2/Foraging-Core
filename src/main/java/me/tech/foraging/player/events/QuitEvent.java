package me.tech.foraging.player.events;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.player.ForagingPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
	private final Foraging foraging;

	public QuitEvent(Foraging foraging) {
		this.foraging = foraging;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent ev) {
		ForagingPlayer player = this.foraging.players.get(ev.getPlayer().getUniqueId());

		player.getSummonManager().getLocationChecker().cancel();

		this.foraging.players.remove(ev.getPlayer().getUniqueId());
	}
}
