package me.tech.foraging.models.database;

import me.tech.foraging.database.repos.SettingsRepo;
import me.tech.foraging.database.repos.SkillsRepo;
import me.tech.foraging.database.repos.StatsRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPlayer {
	public String uuid;

	/**
	 * Get player from database.
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public static DBPlayer fromResult(ResultSet result) throws SQLException {
		DBPlayer dbPlayer = new DBPlayer();

		dbPlayer.uuid = result.getString("uuid");

		return dbPlayer;
	}

	public DBSkills getSkills(SkillsRepo repo) throws SQLException {
		return repo.getSkills(this.uuid);
	}

	public DBStats getStats(StatsRepo repo) throws SQLException {
		return repo.getStats(this.uuid);
	}

	public DBSettings getSettings(SettingsRepo repo) throws SQLException {
		return repo.getSettings(this.uuid);
	}
}