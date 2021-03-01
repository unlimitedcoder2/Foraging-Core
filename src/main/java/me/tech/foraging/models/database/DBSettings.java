package me.tech.foraging.models.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSettings {
	public String uuid;
	public String language;

	public static DBSettings fromResult(ResultSet result) throws SQLException {
		DBSettings dbSettings = new DBSettings();

		dbSettings.uuid = result.getString("uuid");
		dbSettings.language = result.getString("language");

		return dbSettings;
	}
}
