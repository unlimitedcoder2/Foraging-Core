package me.tech.foraging.models.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStats {
	public String uuid;
	public double health;
	public double damage;

	/**
	 * Get stats from database.
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public static DBStats fromResult(ResultSet result) throws SQLException {
		DBStats dbStats = new DBStats();

		dbStats.uuid = result.getString("uuid");
		dbStats.health = result.getDouble("health");
		dbStats.damage = result.getDouble("damage");

		return dbStats;
	}
}