package me.tech.foraging.player;

import me.tech.foraging.Foraging;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SummonManager {
	private final Foraging foraging;
	private final Player player;

	private BukkitTask locationChecker;

	public SummonManager(Foraging foraging, Player player) {
		this.foraging = foraging;
		this.player = player;
	}

	/**
	 * Summons monsters around the player.
	 */
	public void spawnMobs() {
		this.locationChecker = new BukkitRunnable() {
			@Override
			public void run() {
				System.out.println("c");
			}
		}.runTaskTimer(this.foraging, 20*5, 20*5);
	}

	public Player getPlayer() {
		return this.player;
	}

	public BukkitTask getLocationChecker() {
		return this.locationChecker;
	}
}
