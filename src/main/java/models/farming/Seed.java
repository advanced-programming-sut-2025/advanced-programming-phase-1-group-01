package models.farming;

import models.InventoryItem;

public class Seed implements InventoryItem {
    SeedInfo info;

    @Override
    public String getName() {
        return info.getName();
    }
}