package me.tech.foraging.models.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStats {
	public String uuid;
	public int health;
	public int damage;
	public int defense;
	public int speed;
	public int weight;
	public int power;

	/**
	 * Get stats from database.
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public static DBStats fromResult(ResultSet result) throws SQLException {
		DBStats dbStats = new DBStats();

		dbStats.uuid = result.getString("uuid");
		dbStats.health = result.getInt("health");
		dbStats.damage = result.getInt("damage");
		dbStats.defense = result.getInt("defense");
		dbStats.speed = result.getInt("speed");
		dbStats.weight = result.getInt("weight");
		dbStats.power = result.getInt("power");

		return dbStats;
	}
}