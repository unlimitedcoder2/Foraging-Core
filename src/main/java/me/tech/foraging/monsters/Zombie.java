package me.tech.foraging.monsters;

import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterStats;

public class Zombie extends Monster {
	public Zombie(String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats) {
		super(name, level, aggression, stats);
	}
}
