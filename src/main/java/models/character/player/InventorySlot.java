package models.character.player;

import models.InventoryItem;

public class InventorySlot {
    private final Inventory inventory;
    private InventoryItem item;
    private int quantity;

    public InventorySlot(Inventory inventory, String itemName, int quantity) {
        this.inventory = inventory;
        this.item = Inventory.getNewItem(itemName);
        this.quantity = quantity;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        if (amount > 0) quantity += amount;
    }

    public void removeQuantity(int amount) {
        if (amount > 0) quantity -= amount;
        if (quantity == 0) inventory.removeSlot(this);
    }

    public Inventory getInventory() {
        return inventory;
    }
}