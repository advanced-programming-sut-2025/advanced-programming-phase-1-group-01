package models.farming;


import models.enums.Emoji;
import models.Item;
import models.dateTime.Season;

public class Crop extends Plant implements Item, Cloneable {
    private final CropInfo info;
    private CropState state;
    private final Seed seed;
    private boolean becameGiant;

    public Crop(Seed seed) {
        this.seed = seed;
        info = CropInfo.fromSeed(seed);
        state = CropState.HEALTHY;
        becameGiant = false;
        fertilizer = null;
    }

    public Crop(Seed seed, Season season) {
        this.seed = seed;
        info = season.getRandomMixedSeed();
        state = CropState.HEALTHY;
        becameGiant = false;
        fertilizer = null;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public CropInfo getInfo() {
        return info;
    }

    @Override
    public void grow() { // this method should be called every day
        int[] growthStages = info.getStages();
        int regrowthTime = info.getRegrowthTime();

        if (!isFullyGrown()) {
            int currentLevelDays = growthStages[growthLevel - 1];

            if (daysInCurrentLevel >= currentLevelDays) {
                growthLevel++;
            }

            daysInCurrentLevel++;
            return;
        }

        if (info.isOneTime()) return;

        if (hasProduct) return;

        if (daysInCurrentLevel < regrowthTime) {
            daysInCurrentLevel++;
        } else {
            daysInCurrentLevel = 0;
        }
    }

    public boolean isFullyGrown() {
        return growthLevel >= info.getStages().length;
    }

    public int getTotalGrownDays() {
        int totalGrownDays = 0;
        int[] growthStages = info.getStages();

        for (int i = 0; i < growthLevel; i++) {
            totalGrownDays += growthStages[i];
        }

        totalGrownDays += daysInCurrentLevel;
        return totalGrownDays;
    }

    @Override
    public String getSymbol() {
        return Emoji.CORN.getSymbol();
    }


    public boolean isBecameGiant() {
        return becameGiant;
    }

    public void setBecameGiant(boolean becameGiant) {
        this.becameGiant = becameGiant;
    }

    public CropState getState() {
        return state;
    }

    public void setState(CropState state) {
        this.state = state;
    }

    public Seed getSeed() {
        return seed;
    }

    @Override
    public boolean hasProduct() {
//        if (isFullyGrown() && info.isOneTime()) {
//            return true;
//        } else if (isFullyGrown() && daysInCurrentLevel < info.getRegrowthTime()) {
//            return false;
//        } else if (isFullyGrown() && daysInCurrentLevel >= info.getRegrowthTime()) {
//            return true;
//        }
//        return false;
        return hasProduct;
    }

    @Override
    public Crop getProduct() {
        if (!hasProduct()) return null;

        hasProduct = false;
        return this.clone();
    }

    @Override
    public String toString() {
        return info.toString();
    }

    @Override
    public Crop clone() {
        try {
            return (Crop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}