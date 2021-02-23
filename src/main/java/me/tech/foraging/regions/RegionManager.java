package me.tech.foraging.regions;

import me.tech.foraging.Foraging;
import me.tech.foraging.models.region.ForagingRegion;

import java.util.HashMap;

public class RegionManager {
	private Foraging foraging;

	private HashMap<String, ForagingRegion> regions = new HashMap<>();

	public RegionManager(Foraging foraging) {
		this.foraging = foraging;

		this.loadRegions();
	}

	/**
	 * Loads all region data.
	 */
	public void loadRegions() {
	}
}
