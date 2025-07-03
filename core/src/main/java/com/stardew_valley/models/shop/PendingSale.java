package com.stardew_valley.models.shop;

public class PendingSale {
    private final String itemName;
    private final int quantity;
    private final int totalPrice;

    public PendingSale(String itemName, int quantity, int totalPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
