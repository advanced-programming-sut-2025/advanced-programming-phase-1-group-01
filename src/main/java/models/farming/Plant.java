package models.farming;

import models.Item;
import models.animal.ProductQuality;
import models.building.TileObject;

import java.util.List;

public abstract class Plant implements TileObject {
    protected boolean isWatered;
    protected int growthLevel;
    protected int daysInCurrentLevel;
    protected Fertilizer fertilizer;
    protected final ProductQuality quality;

    protected Plant() {
        this.quality = ProductQuality.getRandomProductQuality();
    }

    public abstract FarmingEnum getInfo();

    public void water() {
        isWatered = true;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void resetIsWatered() {
        isWatered = false;
    }

    public int getGrowthLevel() {
        return growthLevel;
    }

    public void incrementGrowthLevel() {
        growthLevel++;
    }

    public int getDaysInCurrentLevel() {
        return daysInCurrentLevel;
    }

    public void incrementDaysInCurrentLevel() {
        daysInCurrentLevel++;
    }

    public abstract void grow();

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public abstract boolean isFullyGrown();

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public boolean isFertilized() {
        return fertilizer != null;
    }

    public ProductQuality getQuality() {
        return quality;
    }

    public abstract boolean hasProduct();

    public abstract List<Item> getProducts();

    @Override
    public String getSymbol() {
        return "P";
    }
}
