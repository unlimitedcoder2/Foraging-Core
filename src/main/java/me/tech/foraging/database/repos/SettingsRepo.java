package me.tech.foraging.database.repos;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;
import me.tech.foraging.models.database.DBSettings;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SettingsRepo {
	private final Foraging foraging;
	private final Database database;
	private final Connection connection;

	public SettingsRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}

	/**
	 * Initialize a player's settings in the database.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public boolean createSettings(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("INSERT INTO settings (uuid, language) VALUES (?, ?)");
		statement.setString(1, uuid);
		statement.setString(2, "en_us");

		return statement.execute();
	}

	public boolean createSettings(UUID uuid) throws SQLException {
		return createSettings(uuid.toString());
	}
	public boolean createSettings(Player player) throws SQLException {
		return createSettings(player.getUniqueId().toString());
	}

	/**
	 * Get a player's settings from the database.
	 * @param uuid
	 * @return
	 * @throws SQLException
	 */
	public DBSettings getSettings(String uuid) throws SQLException {
		PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM settings WHERE uuid = ? LIMIT 1");
		statement.setString(1, uuid);
		ResultSet result = statement.executeQuery();

		if(!result.next()) return null;
		return DBSettings.fromResult(result);
	}

	public DBSettings getSettings(UUID uuid) throws SQLException {
		return getSettings(uuid.toString());
	}
	public DBSettings getSettings(Player player) throws SQLException {
		return getSettings(player.getUniqueId().toString());
	}
}
