package com.stardew_valley.models.fish;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.animal.ProductQuality;

public class Fish implements Item {
    private final FishInfo info;
    private ProductQuality quality;

    public Fish(FishInfo info) {
        this.info = info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    public FishInfo getInfo() {
        return info;
    }

    public ProductQuality getQuality() {
        return quality;
    }

    public void setQuality(ProductQuality quality) {
        this.quality = quality;
    }
}
