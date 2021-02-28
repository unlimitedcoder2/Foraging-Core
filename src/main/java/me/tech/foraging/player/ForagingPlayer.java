package me.tech.foraging.player;

import me.tech.foraging.models.player.ForagingPlayerModel;
import me.tech.foraging.models.player.ForagingPlayerSettings;
import me.tech.foraging.models.player.ForagingPlayerSkills;
import me.tech.foraging.models.player.ForagingPlayerStats;

public class ForagingPlayer extends ForagingPlayerModel {
	public ForagingPlayer(ForagingPlayerStats stats, ForagingPlayerSkills skills, ForagingPlayerSettings settings) {
		super(stats, skills, settings);
	}
}
