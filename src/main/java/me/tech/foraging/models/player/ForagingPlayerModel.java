package me.tech.foraging.models.player;

public abstract class ForagingPlayerModel {
	private final ForagingPlayerStats stats;
	private final ForagingPlayerSkills skills;
	private final ForagingPlayerSettings settings;

	public ForagingPlayerModel(ForagingPlayerStats stats, ForagingPlayerSkills skills, ForagingPlayerSettings settings) {
		this.stats = stats;
		this.skills = skills;
		this.settings = settings;
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
}
