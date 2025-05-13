package models.crafting;

import models.character.player.Inventory;

import java.util.ArrayList;

public class ProcessingCraftedProducts {

    private final ArrayList<UnripeProduct> inventoryItems;
    private final Inventory inventory;

    public ProcessingCraftedProducts(Inventory inventory) {
        inventoryItems = new ArrayList<>();
        this.inventory = inventory;
    }

    private void addInventoryItem(UnripeProduct unripeProduct) {
        inventoryItems.add(unripeProduct);
    }

    private void removeInventoryItem(UnripeProduct unripeProduct) {
        inventoryItems.remove(unripeProduct);
    }

    public void ripeProducts() {
        for (UnripeProduct unripeProduct : inventoryItems) {
            unripeProduct.advanceHourCounter();
        }
    }

    public void addToInventory() {
        for (UnripeProduct unripeProduct : inventoryItems) {
            if (unripeProduct.isRipe()) {
                //@
                removeInventoryItem(unripeProduct);
            }
        }
    }
}
