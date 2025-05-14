package models.farming;

import models.InventoryItem;
import models.enums.Emoji;

public class Crop extends Plant implements InventoryItem {
    private CropInfo info;
    private CropState state;
    private final Seed seed;
    private boolean becameGiant;

    public Crop(Seed seed) {
        this.seed = seed;
        info = CropInfo.fromSeed(seed.getInfo());
        state = CropState.HEALTHY;
    }

    @Override
    public String getName() {
        return info.getName();
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
}