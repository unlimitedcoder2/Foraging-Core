package me.tech.foraging.commands;

import static me.tech.foraging.utils.ChatUtils.color;

import me.tech.foraging.Foraging;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadConfigurationsCommand implements CommandExecutor {
	private final Foraging foraging;

	public ReloadConfigurationsCommand(Foraging foraging) {
		this.foraging = foraging;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		// Console executed command.
		if(!(commandSender instanceof Player)) {
			this.foraging.reloadConfigurations();
			return true;
		}
		Player player = (Player) commandSender;
		if(!player.hasPermission("core.admin")) {
			player.sendMessage(this.foraging.getLangManager().getMSG("core.noPermission"));
			return true;
		}

		this.foraging.reloadConfigurations();
		player.sendMessage(this.foraging.getLangManager().getMSG("core.reloadConfigurations"));
		return true;
	}
}
