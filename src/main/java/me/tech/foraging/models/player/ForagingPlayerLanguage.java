package me.tech.foraging.models.player;

public enum ForagingPlayerLanguage {
	en_us("English", "en_us"),
	uwu("UwU", "uwu");

	private final String name;
	private final String id;

	ForagingPlayerLanguage(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}