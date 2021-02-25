package me.tech.foraging.database.repos;

import me.tech.foraging.Foraging;
import me.tech.foraging.database.Database;

import java.sql.Connection;

public class StatsRepo {
	private final Foraging foraging;
	private Database database;
	private Connection connection;

	public StatsRepo(Foraging foraging, Database database) {
		this.foraging = foraging;
		this.database = database;
		this.connection = database.getConnection();
	}
}
