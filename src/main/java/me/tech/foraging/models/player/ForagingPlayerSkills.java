package me.tech.foraging.models.player;

public class ForagingPlayerSkills {
	private int foragingLevel;
	private double foragingXP;

	private int miningLevel;
	private double miningXP;

	private int fishingLevel;
	private double fishingXP;

	public ForagingPlayerSkills(int foragingLevel, double foragingXP, int miningLevel, double miningXP, int fishingLevel, double fishingXP) {
		this.foragingLevel = foragingLevel;
		this.foragingXP = foragingXP;
		this.miningLevel = miningLevel;
		this.miningXP = miningXP;
		this.fishingLevel = fishingLevel;
		this.fishingXP = fishingXP;
	}

	/**
	 * @return Foraging level.
	 */
	public int getForagingLevel() {
		return foragingLevel;
	}

	/**
	 * @return Foraging XP.
	 */
	public double getForagingXP() {
		return foragingXP;
	}

	/**
	 * Set Foraging level.
	 * @param foragingLevel
	 */
	public void setForagingLevel(int foragingLevel) {
		if(foragingLevel > 50) return;
		this.foragingLevel = foragingLevel;
	}

	public void setForagingXP(double foragingXP) {
		this.foragingXP = foragingXP;
	}

	public int getMiningLevel() {
		return miningLevel;
	}

	public double getMiningXP() {
		return miningXP;
	}

	public void setMiningLevel(int miningLevel) {
		if(miningLevel > 50) return;
		this.miningLevel = miningLevel;
	}

	public void setMiningXP(double miningXP) {
		this.miningXP = miningXP;
	}

	public int getFishingLevel() {
		return fishingLevel;
	}

	public double getFishingXP() {
		return fishingXP;
	}

	public void setFishingLevel(int fishingLevel) {
		if(fishingLevel > 50) return;
		this.fishingLevel = fishingLevel;
	}

	public void setFishingXP(double fishingXP) {
		this.fishingXP = fishingXP;
	}
}
