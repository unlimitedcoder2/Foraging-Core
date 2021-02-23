package me.tech.foraging.items;

import me.tech.foraging.Foraging;
import static me.tech.foraging.utils.ChatUtils.broadcastMessage;

public class ItemManager {
	private final boolean devMode;
	private Foraging foraging;

	public ItemManager(Foraging foraging) {
		this.foraging = foraging;

		this.devMode = this.foraging.getConfig().getBoolean("dev");
	}

	public void initItems() {
		if(this.devMode) {
			return;
		}

		broadcastMessage(foraging, "not in dev.");
	}
}
