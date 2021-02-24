package me.tech.foraging.commands;

import me.tech.foraging.Foraging;
import static me.tech.foraging.utils.ChatUtils.broadcastMessage;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class cooltest implements CommandExecutor {
	private Foraging foraging;

	public cooltest(Foraging foraging) {
		this.foraging = foraging;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
		if(!(commandSender instanceof Player)) return true;
		Player player = (Player) commandSender;
		Location loc = player.getLocation();

		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 2; y++) {
				for(int z = 0; z < 2; z++) {
					loc.add(new Vector(x, y, z));
					if(loc.getBlock().getType() != Material.AIR) {
						broadcastMessage(this.foraging, "ADD:");
						broadcastMessage(this.foraging, loc.getBlock().getType().toString());
						broadcastMessage(this.foraging, loc.toString());
					}
					loc = player.getLocation();
				}
			}
		}

		loc = player.getLocation();

		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 2; y++) {
				for(int z = 0; z < 2; z++) {
					loc.subtract(new Vector(x, y, z));
					if(loc.getBlock().getType() != Material.AIR) {
						broadcastMessage(this.foraging, "SUB:");
						broadcastMessage(this.foraging, loc.getBlock().getType().toString());
						broadcastMessage(this.foraging, loc.toString());
					}
					loc = player.getLocation();
				}
			}
		}

		return true;
	}
}
