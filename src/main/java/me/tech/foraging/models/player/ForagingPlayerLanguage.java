package me.tech.foraging.models.player;

public class ForagingPlayerLanguage {
	private String id;
	private String name;
	private boolean enabled;
	private boolean hidden;

	public ForagingPlayerLanguage(String id, String name, boolean enabled, boolean hidden) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
		this.hidden = hidden;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isHidden() {
		return hidden;
	}
}
