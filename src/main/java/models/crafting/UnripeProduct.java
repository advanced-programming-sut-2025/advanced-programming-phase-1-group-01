package models.crafting;

import models.InventoryItem;

public class UnripeProduct {
    private final InventoryItem inventoryItem;
    private final int harvestTime;

    public UnripeProduct(InventoryItem inventoryItem, int harvestTime) {
        this.inventoryItem = inventoryItem;
        this.harvestTime = harvestTime;
    }

    private boolean isRipe = false;
    private int hourCounter = 0;

    public void advanceHourCounter() {
        hourCounter++;
        if (hourCounter == harvestTime) isRipe = true;
    }

    public boolean isRipe() {
        return isRipe;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }
}
