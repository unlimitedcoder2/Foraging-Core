package me.tech.foraging.database.repos;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import me.tech.foraging.models.database.DBStats;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StatsRepo {
	private final Foraging foraging;
	private Database database;
	private Connection connection;

	public StatsRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}

	/**
	 * Get a players stats.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public DBStats getStats(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM stats WHERE uuid = ? LIMIT 1");
		statement.setString(1, uuid);
		ResultSet result = statement.executeQuery();

		if(!result.next()) return null;
		return DBStats.fromResult(result);
	}

	public DBStats getStats(UUID uuid) throws SQLException {
		return getStats(uuid.toString());
	}

	public DBStats getStats(Player player) throws SQLException {
		return getStats(player.getUniqueId().toString());
	}
}
