package me.tech.foraging.models.region;

public class ForagingRegion {
	private String name;
	private boolean safezone;

	public ForagingRegion(String name, boolean safezone) {
		this.name = name;
		this.safezone = safezone;
	}

	public String getName() {
		return this.name;
	}

	public boolean isSafezone() {
		return this.safezone;
	}
}
