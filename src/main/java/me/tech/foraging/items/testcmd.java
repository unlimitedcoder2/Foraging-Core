package me.tech.foraging.items;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.item.ForagingItemRarity;
import me.tech.foraging.models.item.ForagingItemStats;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class testcmd implements CommandExecutor {
	private final Foraging foraging;

	public testcmd(Foraging foraging) {
		this.foraging = foraging;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		List<String> lore = new ArrayList<>();
		lore.add("HI GAMERS");
		lore.add("xd");

		ForagingItem item = new ForagingItem(
				"Test",
				lore,
				new ItemStack(Material.STICK),
				true,
				ForagingItemRarity.ROYAL,
				new ForagingItemStats(
						5,
						5,
						5,
						5,
						5,
						5
				)
		);

		Player player = (Player) commandSender;
		player.getInventory().addItem(item.getItem());
		return true;
	}
}
