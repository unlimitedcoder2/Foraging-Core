package me.tech.foraging.models.player;

import me.tech.foraging.player.SummonManager;

import java.util.UUID;

public class ForagingPlayer {
	private final UUID uuid;
	private final ForagingPlayerStats stats;
	private final SummonManager summonManager;

	public ForagingPlayer(UUID uuid, ForagingPlayerStats stats, SummonManager summonManager) {
		this.uuid = uuid;
		this.stats = stats;
		this.summonManager = summonManager;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public ForagingPlayerStats getStats() {
		return this.stats;
	}

	public SummonManager getSummonManager() {
		return this.summonManager;
	}
}
