package me.tech.foraging.items;

import me.tech.foraging.Foraging;

import java.util.HashMap;

public class ItemManager {
	private final Foraging foraging;

	private HashMap<String, ForagingItem> items = new HashMap<>();

	public ItemManager(Foraging foraging) {
		this.foraging = foraging;
	}

	public void load() {
		this.items.clear();
	}
}
