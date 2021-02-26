package me.tech.foraging.models.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSkills {
	public String uuid;
	public double foraging_xp;
	public double mining_xp;
	public double fishing_xp;

	/**
	 * Get skills from database.
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public static DBSkills fromResult(ResultSet result) throws SQLException {
		DBSkills dbSkills = new DBSkills();

		dbSkills.uuid = result.getString("uuid");
		dbSkills.foraging_xp = result.getDouble("foraging_xp");
		dbSkills.mining_xp = result.getDouble("mining_xp");
		dbSkills.fishing_xp = result.getDouble("fishing_xp");

		return dbSkills;
	}
}
