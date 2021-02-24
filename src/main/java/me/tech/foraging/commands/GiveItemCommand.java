package me.tech.foraging.commands;

import static me.tech.foraging.utils.ChatUtils.color;

import me.tech.foraging.Foraging;
import me.tech.foraging.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveItemCommand implements CommandExecutor {
	private final Foraging foraging;
	private final ItemManager itemManager;

	public GiveItemCommand(Foraging foraging, ItemManager itemManager) {
		this.foraging = foraging;
		this.itemManager = itemManager;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if(!(commandSender instanceof Player)) return true;
		Player player = (Player) commandSender;

		if(!player.hasPermission("core.giveitem")) {
			player.sendMessage(color(this.foraging.getConfiguration("config").getString("lang.core.invalidPermissions")));
			return true;
		}

		if(strings.length < 2) {
			player.sendMessage(color(this.foraging.getConfiguration("config").getString("lang.commands.giveitem.usage")));
			return true;
		}

		Player receiver = Bukkit.getPlayer(strings[0]);
		if(receiver == null) {
			player.sendMessage(color(this.foraging.getConfiguration("config").getString("lang.core.invalidPlayer")));
			return true;
		}

		this.foraging.getLogger().info(strings[1]);
		if(this.itemManager.items.containsKey(strings[1])) {
			receiver.getInventory().addItem(this.itemManager.items.get(strings[1]).getItem());
			receiver.sendMessage(String.format("You got %s!", strings[1]));
		} else {
			player.sendMessage("Invalid item?");
		}

		return true;
	}
}
