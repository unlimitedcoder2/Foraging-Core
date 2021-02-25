package me.tech.foraging.database.repos;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerRepo {
	private final Foraging foraging;
	private Database database;
	private Connection connection;

	public PlayerRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}

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
}
