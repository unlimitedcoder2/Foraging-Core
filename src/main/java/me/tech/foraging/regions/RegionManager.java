package me.tech.foraging.regions;

import me.tech.foraging.Foraging;

public class RegionManager {
	private Foraging foraging;

	// private HashMap<String, ForagingRegion> regions = new HashMap<>();

	public RegionManager(Foraging foraging) {
		this.foraging = foraging;

		this.loadRegions();
	}

	/**
	 * Loads all region data.
	 */
	public void loadRegions() {
		this.foraging.getLogger().info("pls load regionz");
	}
}
