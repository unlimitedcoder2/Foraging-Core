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

	public String getName() { return name; }
	public String getColor() { return color; }
}
