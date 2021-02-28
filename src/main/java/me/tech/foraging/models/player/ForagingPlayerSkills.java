package me.tech.foraging.models.player;

public class ForagingPlayerSkills {
	private int foragingLevel;
	private double foragingXP;

	private int miningLevel;
	private double miningXP;

	private int fishingLevel;
	private double fishingXP;

	public ForagingPlayerSkills(double foragingXP, double miningXP, double fishingXP) {
		this.foragingXP = foragingXP;
		this.miningXP = miningXP;
		this.fishingXP = fishingXP;
	}

	public int getForagingLevel() {
		return foragingLevel;
	}

	public void setForagingLevel(int foragingLevel) {
		this.foragingLevel = foragingLevel;
	}

	public double getForagingXP() {
		return foragingXP;
	}

	public void setForagingXP(double foragingXP) {
		this.foragingXP = foragingXP;
	}

	public int getMiningLevel() {
		return miningLevel;
	}

	public void setMiningLevel(int miningLevel) {
		this.miningLevel = miningLevel;
	}

	public double getMiningXP() {
		return miningXP;
	}

	public void setMiningXP(double miningXP) {
		this.miningXP = miningXP;
	}

	public int getFishingLevel() {
		return fishingLevel;
	}

	public void setFishingLevel(int fishingLevel) {
		this.fishingLevel = fishingLevel;
	}

	public double getFishingXP() {
		return fishingXP;
	}

	public void setFishingXP(double fishingXP) {
		this.fishingXP = fishingXP;
	}
}
