package models.crafting;

import models.Item;
import models.crafting.enums.CraftingDevices;

public class CraftedProducts {
    private String name;
    private CraftingDevices device;
    private int processingTime;
    private int energy;
    private int sellingPrice;
    private Item inventoryItem;
    private int dayCounterToRipe = 0;
    private boolean isRipe = false;

    public CraftedProducts(CraftingDevices device, int processingTime, int energy, int sellingPrice) {
        this.device = device;
        this.processingTime = processingTime;
        this.energy = energy;
        this.sellingPrice = sellingPrice;
    }

    public void incrementDayCounterToRipe() {
        dayCounterToRipe++;
        if (dayCounterToRipe == processingTime) {
            isRipe = true;
        }
    }

    public Item getInventoryItem() {
        return inventoryItem;
    }
}
