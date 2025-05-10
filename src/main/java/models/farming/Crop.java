package models.farming;

import models.InventoryItem;

public class Crop extends Plant implements InventoryItem {
    private CropType type;
    private CropState state;

    @Override
    public String getName() {
        return type.getName();
    }
}