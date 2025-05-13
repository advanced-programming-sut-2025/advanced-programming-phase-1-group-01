package models.farming;

import models.Item;

import java.util.List;

public class Crop extends Plant implements Item {
    private final CropInfo info;
    private CropState state;
    private final Seed seed;
    private boolean becameGiant;


    public Crop(Seed seed) {
        this.seed = seed;
        info = CropInfo.fromSeed(seed.getInfo());
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
    public boolean hasProduct() {
        return false;
    }

    @Override
    public List<Item> getProducts() {
        return List.of();
    }

    @Override
    public String toString() {
        return info.toString();
    }
}