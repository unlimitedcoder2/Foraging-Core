package me.tech.foraging.database.repos;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import me.tech.foraging.models.database.DBPlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerRepo {
	private final Foraging foraging;
	private final Database database;
	private final Connection connection;

	public PlayerRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}

	/**
	 * Initialize a player in the database.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public boolean createPlayer(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("INSERT INTO players (uuid) VALUES (?)");
		statement.setString(1, uuid);

		return statement.execute();
	}

	public boolean createPlayer(UUID uuid) throws SQLException {
		return createPlayer(uuid.toString());
	}
	public boolean createPlayer(Player player) throws SQLException {
		return createPlayer(player.getUniqueId().toString());
	}

	/**
	 * Get the player.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public DBPlayer getPlayer(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM players WHERE uuid = ? LIMIT 1");
		statement.setString(1, uuid);
		ResultSet result = statement.executeQuery();

		if(!result.next()) return null;
		return DBPlayer.fromResult(result);
	}

	public DBPlayer getPlayer(UUID uuid) throws SQLException {
		return getPlayer(uuid.toString());
	}
	public DBPlayer getPlayer(Player player) throws SQLException {
		return getPlayer(player.getUniqueId().toString());
	}
}