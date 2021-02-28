package me.tech.foraging.commands;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.player.ForagingPlayerLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// TODO: 2/28/2021 Implement database to store user settings.
public class SetLanguageCommand implements CommandExecutor {
	private final Foraging foraging;

	public SetLanguageCommand(Foraging foraging) {
		this.foraging = foraging;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if(!(commandSender instanceof Player)) {
			commandSender.sendMessage("Only player's can issue this command.");
			return true;
		}
		Player player = (Player) commandSender;

		if(strings.length == 0) {
			// TODO: 2/28/2021 Add a player manager to handle ForagingPlayer, en_us hardcoded is just temp.
			player.sendMessage(foraging.getLangManager().get("en_us.commands.setlanguage.usage"));
			return true;
		}
		String newLanguageID = strings[0];
		ForagingPlayerLanguage newLanguage;

		// If there's an error setting the new language
		// then it's probably an invalid id.
		try {
			newLanguage = ForagingPlayerLanguage.valueOf(newLanguageID);
		} catch(IllegalArgumentException ignored) {
			List<String> supportedLanguages = new ArrayList<>();
			for(ForagingPlayerLanguage language : ForagingPlayerLanguage.values())
				supportedLanguages.add(language.getId());

			player.sendMessage(
					foraging.getLangManager().get("en_us.commands.setlanguage.invalidLanguage")
					.replace("{languages}", String.join(", ", supportedLanguages))
			);

			return true;
		}

		player.sendMessage(
				foraging.getLangManager().get("en_us.commands.setlanguage.success")
				.replace("{language}", newLanguage.getName())
		);
		return true;
	}
}
