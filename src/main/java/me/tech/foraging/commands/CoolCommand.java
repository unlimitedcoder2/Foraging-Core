package me.tech.foraging.commands;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.monsters.ForagingMonsterAggression;
import me.tech.foraging.models.monsters.ForagingMonsterEquipment;
import me.tech.foraging.models.monsters.ForagingMonsterStats;
import me.tech.foraging.monsters.Zombie;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CoolCommand implements CommandExecutor {
	private final Foraging foraging;

	public CoolCommand(Foraging foraging) {
		this.foraging = foraging;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		List<String> drops = new ArrayList<>();

		Zombie epicZombie = new Zombie(
				this.foraging,
				"Zombie",
				10,
				ForagingMonsterAggression.AGGRESSIVE,
				new ForagingMonsterStats(
						5,
						5
				),
				new ForagingMonsterEquipment(
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR),
						new ItemStack(Material.AIR)
				),
				drops
		);

		Player player = (Player) commandSender;
		epicZombie.spawnMonster(player.getLocation(), EntityType.ZOMBIE);
		return true;
	}
}
