package me.tech.foraging.models.player;

public class ForagingPlayerSettings {
	private ForagingPlayerLanguage language;

	public ForagingPlayerSettings(ForagingPlayerLanguage language) {
		this.language = language;
	}

	public ForagingPlayerLanguage getLanguage() {
		return language;
	}

	public void setLanguage(ForagingPlayerLanguage language) {
		this.language = language;
	}
}