package me.tech.foraging.player;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.player.ForagingPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class SummonManager {
	private final Foraging foraging;
	private ForagingPlayer foragingPlayer;
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
				searchForSpawnLocations();
				// There are actually valid locations to spawn mobs at.
				if(foragingPlayer.validSpawnLocations.size() > 0) {
					foraging.getLogger().info("There are actual valid spawn locations.");
				}
			}
		}.runTaskTimer(this.foraging, 20*5, 20*5);
	}

	public void searchForSpawnLocations() {
		// Just delete the current shit.
		this.foragingPlayer.validSpawnLocations.clear();

		Location loc = this.player.getLocation();
		// Basic settings.
		int maxY = 3;
		int maxX = 6;
		int maxZ = 6;

		String[] modes = {"ADD", "SUBTRACT"};
		
		// god i fucking hate this but at the same time
		// don't really care.
		for(String mode : modes) {
			for(double x = 0; x < maxX; x++) {
				for(double y = 0; y < maxY; y++) {
					for(double z = 0; z < maxZ; z++) {
						if(mode.equalsIgnoreCase("add")) loc.add(new Vector(x, y, z));
						else loc.subtract(new Vector(x, y, z));

						if(isValidSpawnLocation(loc)) this.foragingPlayer.validSpawnLocations.add(loc);
						loc = this.player.getLocation();
					}
				}
			}
		}
	}

	public boolean isValidSpawnLocation(Location loc) {
		// TODO: 2/23/2021 In the future for specific mobs make it so they can spawn in liquids. 
		if(loc.getBlock().isLiquid() || loc.getBlock().getType() == Material.AIR) return false;
		return true;
	}

	public void setForagingPlayer(ForagingPlayer foragingPlayer) {
		this.foragingPlayer = foragingPlayer;
	}

	public BukkitTask getLocationChecker() {
		return locationChecker;
	}
}
