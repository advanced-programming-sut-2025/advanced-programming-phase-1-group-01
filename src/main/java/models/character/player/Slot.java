package models.character.player;

import models.Item;

public class Slot {
    private final Inventory inventory;
    private Item item;
    private int quantity;

    public Slot(Inventory inventory, String itemName, int quantity) {
        this.inventory = inventory;
        this.item = Inventory.getNewItem(itemName);
        this.quantity = quantity;
    }

    public Slot(Inventory inventory, Item item, int quantity) {
        this.inventory = inventory;
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
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