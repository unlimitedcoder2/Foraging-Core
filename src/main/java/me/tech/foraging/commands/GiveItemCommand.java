package me.tech.foraging.commands;

import static me.tech.foraging.utils.ChatUtils.color;

import me.tech.foraging.Foraging;
import me.tech.foraging.items.ItemManager;
import me.tech.foraging.models.item.ForagingItem;
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
			player.sendMessage(this.foraging.getLangManager().getMSG("core.invalidPermissions"));
			return true;
		}

		if(strings.length < 2) {
			player.sendMessage(this.foraging.getLangManager().getMSG("commands.giveitem.usage"));
			return true;
		}

		Player receiver = Bukkit.getPlayer(strings[0]);
		if(receiver == null) {
			player.sendMessage(this.foraging.getLangManager().getMSG("core.invalidPlayer"));
			return true;
		}
		String itemID = strings[1].toLowerCase();

		if(!this.itemManager.getItems().containsKey(itemID)) {
			player.sendMessage(this.foraging.getLangManager().getMSG("commands.giveitem.invalidItem"));
			return true;
		}

		ForagingItem item = this.itemManager.getItems().get(itemID);
		String itemName = color(String.format("%s%s", item.getRarity().getBoldColor(), item.getName()));

		receiver.getInventory().addItem(item.getItem());
		player.sendMessage(this.foraging.getLangManager().getMSG("commands.giveitem.gaveItem")
			.replace("{receiver}", receiver.getName())
			.replace("{itemName}", itemName)
		);
		return true;
	}
}
