package models.crafting;

import models.Item;

public class UnripeProduct {
    private final Item item;
    private final int harvestTime;

    public UnripeProduct(Item item, int harvestTime) {
        this.item = item;
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

    public Item getInventoryItem() {
        return item;
    }
}
