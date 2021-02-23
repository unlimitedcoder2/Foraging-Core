package me.tech.foraging.monsters;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterEquipment;
import me.tech.foraging.models.monsters.ForagingMonsterStats;

import java.util.List;

public class Zombie extends Monster {
	public Zombie(Foraging foraging, String name, int level, ForagingMonsterAggression aggression, ForagingMonsterStats stats, ForagingMonsterEquipment equipment, List<String> drops) {
		super(foraging, name, level, aggression, stats, equipment, drops);
	}
}
