package models.farming;

import models.Item;
import models.animal.ProductQuality;
import models.building.TileObject;

public abstract class Plant implements TileObject {
    protected boolean isWatered;
    protected int growthLevel;
    protected int daysInCurrentLevel;
    protected Fertilizer fertilizer;
    protected final ProductQuality quality;
    protected boolean hasProduct;

    protected Plant() {
        this.quality = ProductQuality.getRandomProductQuality();
        growthLevel = 1;
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

    public boolean hasProduct() {
        return hasProduct;
    }

    public abstract Item getProduct();

    public abstract String getName();

    public abstract void growFull();
}
