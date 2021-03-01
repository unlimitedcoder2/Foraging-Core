package me.tech.foraging.player.events;

import me.tech.foraging.Foraging;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
	private final Foraging foraging;

	public PlayerQuit(Foraging foraging) {
		this.foraging = foraging;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent ev) {
		Player player = ev.getPlayer();

		foraging.getPlayers().remove(player.getUniqueId());
		foraging.getLogger().info(String.format("%s has left the server and has been removed from the hashmap.", player.getName()));
	}
}
