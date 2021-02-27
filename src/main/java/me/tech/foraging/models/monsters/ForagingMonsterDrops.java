package me.tech.foraging.models.monsters;

public class ForagingMonsterDrops {
	private String id;
	private int amount;
	private double chance;

	public ForagingMonsterDrops(String id, int amount, double chance) {
		this.id = id;
		this.amount = amount;
		this.chance = chance;
	}

	public String getID() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	public double getChance() {
		return chance;
	}
}
