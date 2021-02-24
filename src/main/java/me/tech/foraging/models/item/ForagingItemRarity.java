package me.tech.foraging.models.item;

public enum ForagingItemRarity {
	COMMON ("COMMON", "&f"),
	UNCOMMON ("UNCOMMON", "&a"),
	RARE ("RARE", "&9"),
	EPIC ("EPIC", "&c"),
	LEGENDARY ("LEGENDARY", "&6"),
	ROYAL ("ROYAL", "&5");

	private final String name;
	private final String color;

	ForagingItemRarity(String name, String color) {
		this.name = name;
		this.color = color;
	}

	/** @return Rarity name. */
	public String getName() { return name; }
	/** @return Rarity colorcode. */
	public String getColor() { return color; }
	/** @return Rarity colorcode in bold. */
	public String getBoldColor() { return color+"&l"; }
}
