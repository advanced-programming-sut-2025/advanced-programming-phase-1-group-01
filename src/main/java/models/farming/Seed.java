package models.farming;

import models.InventoryItem;

public class Seed implements InventoryItem {
    private final SeedInfo info;

    public Seed(SeedInfo info) {
        this.info = info;
    }

    public SeedInfo getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return info.getName();
    }
}