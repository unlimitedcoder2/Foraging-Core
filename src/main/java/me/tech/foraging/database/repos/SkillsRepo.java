package me.tech.foraging.database.repos;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import me.tech.foraging.models.database.DBSkills;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SkillsRepo {
	private final Foraging foraging;
	private Database database;
	private Connection connection;

	public SkillsRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}

	public boolean createSkills(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("INSERT INTO skills (uuid, foraging_xp, mining_xp, fishing_xp) VALUES (?, ?, ?, ?)");
		statement.setString(1, uuid);
		statement.setDouble(2, 0);
		statement.setDouble(3, 0);
		statement.setDouble(4, 0);

		return statement.execute();
	}

	public boolean createSkills(UUID uuid) throws SQLException {
		return createSkills(uuid.toString());
	}

	public boolean createSkills(Player player) throws SQLException {
		return createSkills(player.getUniqueId().toString());
	}

	/**
	 * Get a players skills.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public DBSkills getSkills(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM skills WHERE uuid = ? LIMIT 1");
		statement.setString(1, uuid);
		ResultSet result = statement.executeQuery();

		if(!result.next()) return null;
		return DBSkills.fromResult(result);
	}

	public DBSkills getSkills(UUID uuid) throws SQLException {
		return getSkills(uuid.toString());
	}

	public DBSkills getSkills(Player player) throws SQLException {
		return getSkills(player.getUniqueId().toString());
	}

	public void setForagingXP(double foragingXP) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("UPDATE skills SET foraging_xp = foraging_xp WHERE uuid = ?");
	}
}