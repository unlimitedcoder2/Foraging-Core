package me.tech.foraging.models.player;

public class ForagingPlayerSettings {
	ForagingPlayerLanguage language;

	public ForagingPlayerSettings(ForagingPlayerLanguage language) {
		this.language = language;
	}

	public ForagingPlayerLanguage getLanguage() {
		return language;
	}
}