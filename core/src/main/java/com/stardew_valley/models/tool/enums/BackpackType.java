package com.stardew_valley.models.tool.enums;

public enum BackpackType {
    SMALL(12), BIG(24), DELUXE(200);

    private int capacity;

    BackpackType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
