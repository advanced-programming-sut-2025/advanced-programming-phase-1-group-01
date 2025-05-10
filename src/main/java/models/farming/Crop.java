package models.farming;

import models.InventoryItem;

public class Crop extends Plant implements InventoryItem {
    private CropType type;
    private CropState state;

    public Crop(int amount) {
        super(amount);
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getSymbol() {
        return "";
    }
}