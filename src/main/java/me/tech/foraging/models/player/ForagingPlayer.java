package me.tech.foraging.models.player;

import me.tech.foraging.player.SummonManager;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class ForagingPlayer {
	private final ForagingPlayerStats stats;
	private final ForagingPlayerSkills skills;
	private ForagingPlayerSettings settings;
	private final SummonManager summonManager;
	public List<Location> validSpawnLocations = new ArrayList<>();

	public ForagingPlayer(ForagingPlayerStats stats, ForagingPlayerSkills skills, SummonManager summonManager) {
		this.stats = stats;
		this.skills = skills;
		this.settings = new ForagingPlayerSettings();
		this.summonManager = summonManager;
	}

	public ForagingPlayerStats getStats() {
		return stats;
	}

	public ForagingPlayerSkills getSkills() {
		return skills;
	}

	public ForagingPlayerSettings getSettings() {
		return settings;
	}

	public SummonManager getSummonManager() {
		return summonManager;
	}

	public List<Location> getValidSpawnLocations() {
		return validSpawnLocations;
	}
}
