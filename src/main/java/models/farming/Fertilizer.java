package models.farming;

import models.InventoryItem;

public class Fertilizer implements InventoryItem {
    FertilizerType type;

    @Override
    public String getName() {
        return type.toString();
    }
}
