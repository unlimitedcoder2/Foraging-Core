package me.tech.foraging.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * User: 00
 * Date: 23/02/2021
 * Time: 16:40
 */
public class ChatUtils {
	public static void broadcastMessage(Collection<? extends Player> players, String msg) {
		for (Player player : players) {
			player.sendMessage(msg);
		}
	}

	public static void broadcastMessage(Server server, String msg) {
		broadcastMessage(server.getOnlinePlayers(), msg);
	}

	public static void broadcastMessage(JavaPlugin plugin, String msg) {
		broadcastMessage(plugin.getServer(), msg);
	}

	public static String color(String m) {
		return ChatColor.translateAlternateColorCodes('&', m);
	}

	public static Component text(String m) {
		return Component.text(m);
	}
}
