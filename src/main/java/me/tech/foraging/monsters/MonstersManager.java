package me.tech.foraging.monsters;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonstersManager {
	private final Foraging foraging;

	private final HashMap<String, Monster> monsters = new HashMap<>();

	public MonstersManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public void initMonsters() {
		FileConfiguration monstersConfig = this.foraging.getConfiguration("monsters");
		this.monsters.clear();

		for(String monsterID : monstersConfig.getConfigurationSection("monsters").getKeys(false)) {
			ConfigurationSection monster = monstersConfig.getConfigurationSection(String.format("monsters.%s", monsterID));
			ConfigurationSection drops = monster.getConfigurationSection("drops");
			ConfigurationSection equipment = monster.getConfigurationSection("equipment");

			List<ForagingMonsterDrops> dropsList = new ArrayList<>();
			// Monster has drops.

			if(drops != null) {
				for(String dropID : drops.getKeys(false)) {
					ConfigurationSection drop = drops.getConfigurationSection(dropID);

					String itemID = drop.getString("id");
					int dropAmount = drop.getInt("amount");
					double dropChance = drop.getDouble("chance");

					dropsList.add(new ForagingMonsterDrops(itemID, dropAmount, dropChance));
				}
			}

			Monster foragingMonster = new Monster(
					this.foraging,
					monster.getString("name"),
					monster.getInt("level"),
					EntityType.valueOf(monster.getString("entity")),
					ForagingMonsterAggression.valueOf(monster.getString("aggression")),
					new ForagingMonsterStats(
							monster.getDouble("health"),
							monster.getDouble("damage")
					),
					/* owo */
					new ForagingMonsterEquipment(
							/* TODO: 2/27/2021 Implement system to allow for glowing items. */
							new ItemStack(Material.valueOf(
									equipment.getString("helmet") != null ? equipment.getString("helmet").toUpperCase() : "AIR")
							),
							new ItemStack(Material.valueOf(
									equipment.getString("chestplate") != null ? equipment.getString("chestplate").toUpperCase() : "AIR")
							),
							new ItemStack(Material.valueOf(
									equipment.getString("leggings") != null ? equipment.getString("leggings").toUpperCase() : "AIR")
							),
							new ItemStack(Material.valueOf(
									equipment.getString("boots") != null ? equipment.getString("boots").toUpperCase() : "AIR")
							),
							new ItemStack(Material.valueOf(
									equipment.getString("mainHand") != null ? equipment.getString("mainHand").toUpperCase() : "AIR")
							),
							new ItemStack(Material.valueOf(
									equipment.getString("offHand") != null ? equipment.getString("offHand").toUpperCase() : "AIR")
							)
					),
					dropsList
			);
			this.foraging.getLogger().info(String.format("Added monster %s!", monsterID));
			this.monsters.put(monsterID, foragingMonster);

		}
	}

	public HashMap<String, Monster> getMonsters() {
		return monsters;
	}
}
