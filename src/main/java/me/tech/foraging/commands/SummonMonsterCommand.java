package me.tech.foraging.commands;

import me.tech.foraging.Foraging;
import me.tech.foraging.monsters.Monster;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SummonMonsterCommand implements CommandExecutor {
	private final Foraging foraging;

	public SummonMonsterCommand(Foraging foraging) {
		this.foraging = foraging;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if(!(commandSender instanceof Player)) {
			commandSender.sendMessage("Only players can issue this command.");
			return true;
		}
		Player player = (Player) commandSender;

		if(!player.hasPermission("core.admin")) {
			player.sendMessage(this.foraging.getLangManager().getMSG("core.invalidPermissions"));
			return true;
		}

		if(strings.length == 0) {
			player.sendMessage(this.foraging.getLangManager().getMSG("commands.summonmonster.usage"));
			return true;
		}
		String monsterID = String.join("_", strings);
		this.foraging.getLogger().info(String.format("Monster ID got was %s", monsterID));

		if(!this.foraging.getMonsters().containsKey(monsterID)) {
			player.sendMessage(this.foraging.getLangManager().getMSG("commands.summonmonster.invalidMonster"));
			return true;
		}
		Monster monster = this.foraging.getMonsters().get(monsterID);

		monster.spawnMonster(player.getLocation());

		player.sendMessage(
				this.foraging.getLangManager().getMSG("commands.summonmonster.success")
				.replace("{aggression_color}", monster.getAggression().getColor())
				.replace("{monster}", monster.getName())
		);

		return true;
	}
}

