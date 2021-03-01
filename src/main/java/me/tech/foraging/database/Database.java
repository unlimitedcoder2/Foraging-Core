package me.tech.foraging.database;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.repos.PlayerRepo;
import me.tech.foraging.database.repos.SettingsRepo;
import me.tech.foraging.database.repos.SkillsRepo;
import me.tech.foraging.database.repos.StatsRepo;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private final Foraging foraging;
	private String databaseType;
	private Connection connection;

	private PlayerRepo playerRepo;
	private SkillsRepo skillsRepo;
	private StatsRepo statsRepo;
	private SettingsRepo settingsRepo;

	public Database(Foraging foraging) {
		this.foraging = foraging;
	}

	/**
	 * Establish a connection with the database of choice.
	 */
	public void establishConnection() {
		this.databaseType = this.foraging.getConfig().getString("db.type");

		try {
			if(this.databaseType.equalsIgnoreCase("MySQL"))
				this.createMySQL();
			else if(this.databaseType.equalsIgnoreCase("SQLite"))
				this.createSQLite();
			else {
				this.createSQLite();
				this.foraging.getLogger().severe(String.format("An invalid database type of '%s' was set, defaulting to SQLite.", this.databaseType));
			}
			this.setupRepos();
		} catch(SQLException ex) { ex.printStackTrace(); }
	}

	/**
	 * Create a connection to the MySQL database.
	 * @throws SQLException Failed to connect to database.
	 */
	private void createMySQL() throws SQLException {
		ConfigurationSection dbConfig = this.foraging.getConfig().getConfigurationSection("db");
		// magik
		String host = dbConfig.getString("host");
		int port = dbConfig.getInt("port");
		String dbName = dbConfig.getString("name");
		String username = dbConfig.getString("username");
		String password = dbConfig.getString("password");

		this.connection = DriverManager.getConnection(
				String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", host, port, dbName, username, password)
		);
	}

	/**
	 * Create a SQLite database if not already created.
	 * @throws SQLException Failed to connect to database.
	 */
	private void createSQLite() throws SQLException {
		// Name of database file.
		String dbFileName = this.foraging.getConfig().getString("db.file");
		if(dbFileName == null) dbFileName = "foraging.db";

		File dbFile = new File(this.foraging.getDataFolder(), dbFileName);
		// Check to see if a SQLite database already exists.
		if(!dbFile.exists()) {
			// Save foraging.db from plugins resource folder to data folder.
			this.foraging.saveResource("foraging.db", true);
			dbFile = new File(this.foraging.getDataFolder(), "foraging.db");
			// File name is automatically set to foraging.db so
			// it being renamed to itself is fine.
			File newFileName = new File(this.foraging.getDataFolder(), dbFileName);
			dbFile.renameTo(newFileName);
		}

		this.connection = DriverManager.getConnection(
				String.format("jdbc:sqlite:%s/%s", this.foraging.getDataFolder(), dbFileName)
		);
	}

	public void setupRepos() {
		this.playerRepo = new PlayerRepo(foraging, this);
		this.skillsRepo = new SkillsRepo(foraging, this);
		this.statsRepo = new StatsRepo(foraging, this);
		this.settingsRepo = new SettingsRepo(foraging, this);
	}

	public Connection getConnection() {
		return connection;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public PlayerRepo getPlayerRepo() {
		return playerRepo;
	}

	public SkillsRepo getSkillsRepo() {
		return skillsRepo;
	}

	public StatsRepo getStatsRepo() {
		return statsRepo;
	}

	public SettingsRepo getSettingsRepo() {
		return settingsRepo;
	}
}
