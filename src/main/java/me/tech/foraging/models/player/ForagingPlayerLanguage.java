package me.tech.foraging.models.player;

public enum ForagingPlayerLanguage {
	en("English");

	private final String name;

	ForagingPlayerLanguage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}