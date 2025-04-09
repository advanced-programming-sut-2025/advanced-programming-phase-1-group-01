package models;

public class Ability {
    private int farmingLevel;
    private int miningLevel;
    private int fishingLevel;
    private int hikingLevel;

    public int getFarmingLevel() {
        return farmingLevel;
    }

    public void setFarmingLevel(int farmingLevel) {
        this.farmingLevel = farmingLevel;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public void setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
    }

    public int getFishingLevel() {
        return fishingLevel;
    }

    public void setFishingLevel(int fishingLevel) {
        this.fishingLevel = fishingLevel;
    }

    public int getHikingLevel() {
        return hikingLevel;
    }

    public void setHikingLevel(int hikingLevel) {
        this.hikingLevel = hikingLevel;
    }
}
