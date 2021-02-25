package me.tech.foraging.models.monsters;

public enum ForagingMonsterAggression {
	PASSIVE ("Passive", "&a"),
	NEUTRAL ("Neutral", "&e"),
	AGGRESSIVE ("Aggressive", "&c");

	private final String name;
	private final String color;

	ForagingMonsterAggression(String name, String color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getBoldColor() {
		return "&l"+color;
	}
}
