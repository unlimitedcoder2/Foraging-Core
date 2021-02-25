package me.tech.foraging.models.player;

import me.tech.foraging.player.SummonManager;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class ForagingPlayer {
	private ForagingPlayerStats stats;
	private ForagingPlayerSkills skills;
	private final SummonManager summonManager;
	public List<Location> validSpawnLocations = new ArrayList<>();

	public ForagingPlayer(ForagingPlayerStats stats, ForagingPlayerSkills skills, SummonManager summonManager) {
		this.stats = stats;
		this.skills = skills;
		this.summonManager = summonManager;
	}

	public ForagingPlayerStats getStats() {
		return stats;
	}

	public ForagingPlayerSkills getSkills() {
		return skills;
	}

	public SummonManager getSummonManager() {
		return summonManager;
	}

	public List<Location> getValidSpawnLocations() {
		return validSpawnLocations;
	}
}
