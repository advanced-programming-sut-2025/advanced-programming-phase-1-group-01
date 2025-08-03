package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.enums.Emoji;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.dateTime.Season;

public class Crop extends Plant implements Item, Cloneable {
    private final CropInfo info;
    private CropState state;
    private final Seed seed;
    private boolean becameGiant;
    private boolean isPlanted;

    public Crop(Seed seed) {
        this.seed = seed;
        info = CropInfo.fromSeed(seed);
        state = CropState.HEALTHY;
        becameGiant = false;
        fertilizer = null;
    }

    public Crop(SeedInfo seedInfo) {
        this.seed = seedInfo.toItem();
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
    public void growFull() {
        for (int i = 0; i < info.getTotalHarvestTime(); i++) {
            grow();
        }
    }

    @Override
    public int getPrice() {
        return info.getBaseSellPrice();
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

        for (int i = 0; i < (growthLevel - 1); i++) {
            totalGrownDays += growthStages[i];
        }

        totalGrownDays += daysInCurrentLevel;
        return totalGrownDays;
    }

    @Override
    public Texture getTexture() {
//         if (isPlanted) {
//             return info.getTextureByStage(growthLevel);
//         }
//         return info.getCropTexture();
        return AssetManager.getAssetManager().defaultTexture();
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

    public boolean isPlanted() {
        return isPlanted;
    }

    public void setPlanted(boolean planted) {
        isPlanted = planted;
    }
}
