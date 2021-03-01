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
	private final Database database;
	private final Connection connection;

	public StatsRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}

	/**
	 * Initialize a players stats in the database.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public boolean createStats(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("INSERT INTO stats (uuid, health, damage, defense, speed, weight, power) VALUES (?, ?, ?, ?, ?, ?, ?)");
		statement.setString(1, uuid);
		// Health
		statement.setInt(2, 20);
		// Damage
		statement.setInt(3, 5);
		// Defense
		statement.setInt(4, 0);
		// Speed
		statement.setInt(5, 100);
		// Weight
		statement.setInt(6, 0);
		// Power
		statement.setInt(7, 0);

		return statement.execute();
	}

	public boolean createStats(UUID uuid) throws SQLException {
		return createStats(uuid.toString());
	}
	public boolean createStats(Player player) throws SQLException {
		return createStats(player.getUniqueId().toString());
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