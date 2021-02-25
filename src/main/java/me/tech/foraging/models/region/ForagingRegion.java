package me.tech.foraging.models.region;

public class ForagingRegion {
	private String name;
	private boolean safezone;

	public ForagingRegion(String name, boolean safezone) {
		this.name = name;
		this.safezone = safezone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSafezone() {
		return safezone;
	}

	public void setSafezone(boolean safezone) {
		this.safezone = safezone;
	}
}
