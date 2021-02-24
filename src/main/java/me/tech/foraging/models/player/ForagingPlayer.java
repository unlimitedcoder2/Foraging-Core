package me.tech.foraging.models.player;

import me.tech.foraging.player.SummonManager;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class ForagingPlayer {
	private final ForagingPlayerStats stats;
	private final SummonManager summonManager;
	public List<Location> validSpawnLocations = new ArrayList<>();

	public ForagingPlayer(ForagingPlayerStats stats, SummonManager summonManager) {
		this.stats = stats;
		this.summonManager = summonManager;
	}

	public ForagingPlayerStats getStats() {
		return this.stats;
	}

	public SummonManager getSummonManager() {
		return this.summonManager;
	}

	public List<Location> getValidSpawnLocations() {
		return this.validSpawnLocations;
	}
}
