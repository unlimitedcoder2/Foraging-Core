package me.tech.foraging;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
	private final Foraging foraging;
	private Connection connection;

	public DatabaseHandler(Foraging foraging) {
		this.foraging = foraging;
	}

	public void establishConnection() {
		// If a database doesn't exist create one.
		if(!this.databaseExists()) {
			try { this.createDatabase(); }
			catch(SQLException ex) {
				ex.printStackTrace();
				return;
			}
		}
		// Establish new connection to database.
		try {
			String url = String.format("jdbc:sqlite:%s/foraging.db", this.foraging.getDataFolder());

			this.connection = DriverManager.getConnection(url);
			this.foraging.getLogger().info("Established connection with database.");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Creates a new SQLite database.
	 */
	private void createDatabase() throws SQLException {
		String url = String.format("jdbc:sqlite:%s/foraging.db", this.foraging.getDataFolder());
		Connection conn = null;
		DatabaseMetaData meta = null;

		conn = DriverManager.getConnection(url);
		if(conn != null) {
			meta = conn.getMetaData();
			this.foraging.getLogger().info(String.format("%s was created.", meta.getDriverName()));
		} else throw new SQLException("Connection was null.");
		conn.close();
	}

	/**
	 * @return If the database exists.
	 */
	private boolean databaseExists() {
		File file = new File(this.foraging.getDataFolder(), "foraging.db");
		return file.exists();
	}

	public Connection getConnection() {
		return connection;
	}
}
