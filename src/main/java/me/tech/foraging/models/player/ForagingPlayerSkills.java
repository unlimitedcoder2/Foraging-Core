package me.tech.foraging.models.player;

import java.security.InvalidParameterException;

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

	public int getForagingLevel() {
		return foragingLevel;
	}

	public double getForagingXP() {
		return foragingXP;
	}

	public void setForagingLevel(int foragingLevel) throws InvalidParameterException {
		if(foragingLevel > 50) throw new InvalidParameterException("Level cannot be above 50.");
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

	public void setMiningLevel(int miningLevel) throws InvalidParameterException {
		if(miningLevel > 50) throw new InvalidParameterException("Level cannot be above 50.");
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

	public void setFishingLevel(int fishingLevel) throws InvalidParameterException {
		if(fishingLevel > 50) throw new InvalidParameterException("Level cannot be above 50.");
		this.fishingLevel = fishingLevel;
	}

	public void setFishingXP(double fishingXP) {
		this.fishingXP = fishingXP;
	}
}
