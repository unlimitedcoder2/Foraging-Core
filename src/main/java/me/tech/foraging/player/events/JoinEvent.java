package me.tech.foraging.player.events;

import static me.tech.foraging.utils.ChatUtils.text;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import me.tech.foraging.models.database.DBPlayer;
import me.tech.foraging.models.database.DBSkills;
import me.tech.foraging.models.database.DBStats;
import me.tech.foraging.models.player.ForagingPlayer;
import me.tech.foraging.models.player.ForagingPlayerSkills;
import me.tech.foraging.models.player.ForagingPlayerStats;
import me.tech.foraging.player.SummonManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class JoinEvent implements Listener {
	private final Foraging foraging;
	private Database database;

	public JoinEvent(Foraging foraging) {
		this.foraging = foraging;
		this.database = foraging.getDatabase();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		ev.joinMessage(
			text(this.foraging.getLangManager().getMSG("player.join")
			.replace("{player}", ev.getPlayer().getName()))
		);

		try {
			DBPlayer dbPlayer = this.database.getPlayerRepo().getPlayer(ev.getPlayer());
			if(dbPlayer == null) {
				this.database.getPlayerRepo().createPlayer(ev.getPlayer());
				this.database.getSkillsRepo().createSkills(ev.getPlayer());
				this.database.getStatsRepo().createStats(ev.getPlayer());

				this.foraging.getLogger().info("Created player " + ev.getPlayer().getName());

				dbPlayer = this.database.getPlayerRepo().getPlayer(ev.getPlayer());
			}

			DBStats dbStats = dbPlayer.getStats(this.database.getStatsRepo());
			DBSkills dbSkills = dbPlayer.getSkills(this.database.getSkillsRepo());

			this.foraging.players.put(
				ev.getPlayer().getUniqueId(),
				new ForagingPlayer(
					new ForagingPlayerStats(dbStats.health, dbStats.damage),
					new ForagingPlayerSkills(0, dbSkills.foraging_xp, 0, dbSkills.mining_xp, 0, dbSkills.fishing_xp),
					new SummonManager(this.foraging, ev.getPlayer())
				)
			);
			this.foraging.getLogger().info(String.format("Shoved %s into players hashmap.", ev.getPlayer().getUniqueId()));

			ForagingPlayer foragingPlayer = this.foraging.players.get(ev.getPlayer().getUniqueId());

			foragingPlayer.getSummonManager().setForagingPlayer(foragingPlayer);
			foragingPlayer.getSummonManager().spawnMobs();

	    } catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
