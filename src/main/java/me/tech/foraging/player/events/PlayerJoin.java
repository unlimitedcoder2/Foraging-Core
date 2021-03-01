package me.tech.foraging.player.events;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import me.tech.foraging.models.database.DBPlayer;
import me.tech.foraging.models.database.DBSettings;
import me.tech.foraging.models.database.DBSkills;
import me.tech.foraging.models.database.DBStats;
import me.tech.foraging.models.player.ForagingPlayerLanguage;
import me.tech.foraging.models.player.ForagingPlayerSettings;
import me.tech.foraging.models.player.ForagingPlayerSkills;
import me.tech.foraging.models.player.ForagingPlayerStats;
import me.tech.foraging.player.ForagingPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerJoin implements Listener {
	private final Foraging foraging;
	private final Database database;

	public PlayerJoin(Foraging foraging) {
		this.foraging = foraging;
		this.database = foraging.getDatabase();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		Player player = ev.getPlayer();

		try {
			DBPlayer dbPlayer = this.database.getPlayerRepo().getPlayer(player);
			// Player is not in the database.
			if(dbPlayer == null) {
				this.database.getPlayerRepo().createPlayer(player);
				this.database.getSkillsRepo().createSkills(player);
				this.database.getStatsRepo().createStats(player);
				this.database.getSettingsRepo().createSettings(player);

				foraging.getLogger().info(String.format("Initialized player %s into the database.", player.getName()));
				dbPlayer = this.database.getPlayerRepo().getPlayer(player);
			}

			DBSkills dbSkills = dbPlayer.getSkills(database.getSkillsRepo());
			DBStats dbStats = dbPlayer.getStats(database.getStatsRepo());
			DBSettings dbSettings = dbPlayer.getSettings(database.getSettingsRepo());

			ForagingPlayerLanguage language = foraging.getLangManager().getSupportedLanguages().get(dbSettings.language);

			foraging.getPlayers().put(
					player.getUniqueId(),
					new ForagingPlayer(
							new ForagingPlayerStats(dbStats.health, dbStats.damage, dbStats.defense, dbStats.speed, dbStats.weight, dbStats.power),
							new ForagingPlayerSkills(dbSkills.foraging_xp, dbSkills.mining_xp, dbSkills.fishing_xp),
							new ForagingPlayerSettings(language)
					)
			);
			// DEV
			foraging.getLogger().info(String.format("%s has joined the game and has been initalized properly!", player.getName()));
		} catch(SQLException ex) {
			ex.printStackTrace();
			// DEV
			foraging.getLogger().severe(String.format("There was a problem initializing %s into the server!", player.getName()));
		}
	}
}
