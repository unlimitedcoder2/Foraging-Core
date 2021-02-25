package me.tech.foraging;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
	private final Foraging foraging;
	private Connection connection;

	public DatabaseHandler(Foraging foraging) {
		this.foraging = foraging;
	}

	public void establishConnection() {
		String dbType = this.foraging.getConfig().getString("db.type");

		try {
			switch(dbType.toLowerCase()) {
				case "mysql":
					this.establishMySQLConnection();
					break;
				case "sqlite":
				default:
					this.establishSQLiteConnection();
					dbType = "SQLite";
			}

			this.foraging.getLogger().info(String.format("Established connection with %s database.", dbType));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void establishMySQLConnection() throws SQLException {
		ConfigurationSection dbConfig = foraging.getConfig().getConfigurationSection("db");
		String host = dbConfig.getString("host");
		int port = dbConfig.getInt("port");
		String dbName = dbConfig.getString("name");
		String username = dbConfig.getString("username");
		String password = dbConfig.getString("password");

		this.connection = DriverManager.getConnection(
			String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", host, port, dbName, username, password)
		);
	}

	private void establishSQLiteConnection() throws SQLException {
		// Establish new connection to database.
		String dbFileName = this.foraging.getConfig().getString("db.file");
		if(dbFileName == null) dbFileName = "foraging.db";

		File dbFile = new File(this.foraging.getDataFolder(), dbFileName);
		// Check to see if SQLite DB File is created.
		if(!dbFile.exists()) {
			this.foraging.saveResource("foraging.db", false);
			// Set DB File to foraging.db.
			dbFile = new File(this.foraging.getDataFolder(), "foraging.db");
			// Rename foraging.db to whatever db.file is, if it's foraging.db
			// it will just rename to the same file.
			File newName = new File(this.foraging.getDataFolder(), dbFileName);
			//noinspection ResultOfMethodCallIgnored
			dbFile.renameTo(newName);
		}
		// Establish the connection with sqlite.
		String url = String.format("jdbc:sqlite:%s/%s", this.foraging.getDataFolder(), dbFileName);
		this.connection = DriverManager.getConnection(url);
	}

	public Connection getConnection() {
		return connection;
	}
}
