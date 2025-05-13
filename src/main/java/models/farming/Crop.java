package models.farming;

import models.InventoryItem;

public class Crop extends Plant implements InventoryItem {
    private final CropInfo info;
    private CropState state;
    private final Seed seed;
    private boolean becameGiant;

    public Crop(Seed seed) {
        this.seed = seed;
        info = CropInfo.fromSeed(seed.getInfo());
        state = CropState.HEALTHY;
        becameGiant = false;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    public CropInfo getInfo() {
        return info;
    }

    @Override
    public void grow() { // this method should be called every day
        int[] growthStages = info.getStages();

        if (isFullyGrown()) return;

        int currentLevelDays = growthStages[growthLevel];

        if (daysInCurrentLevel >= currentLevelDays) {
            growthLevel++;
        }

        daysInCurrentLevel++;
    }

    public boolean isFullyGrown() {
        return growthLevel >= info.getStages().length;
    }

    @Override
    public String getSymbol() {
        return "";
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
    public String toString() {
        return info.toString();
    }
}