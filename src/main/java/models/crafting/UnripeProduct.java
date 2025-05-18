package models.crafting;

import models.Item;

public class UnripeProduct {
    private final Item item;
    private int harvestTime;

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

    public void setHarvestHours(int hour, int harvestTime) {
        if (harvestTime == -1) this.harvestTime = 33 - hour;
    }

    public int getHarvestTime() {
        return harvestTime;
    }
}
