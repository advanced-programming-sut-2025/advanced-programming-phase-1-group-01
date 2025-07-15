package com.stardew_valley.models.animal;

import com.stardew_valley.models.Size;

public enum AnimalHouseType {

    COOP(4, new Size(3, 3),4000,300,100),
    BIG_COOP(8, new Size(4, 4),10000,400,150),
    DELUXE_COOP(12, new Size(5, 5),20000,500,200),
    BARN(4, new Size(3, 3),6000,350,150),
    BIG_BARN(8, new Size(4, 4),12000,450,200),
    DELUXE_BARN(12, new Size(5, 5),25000,550,300);

    private final int capacity;
    private final Size size;
    private final int Price;
    private final int woodCount;
    private final int stoneCount;

    AnimalHouseType(int capacity, Size size, int price, int woodCount, int stoneCount) {
        this.capacity = capacity;
        this.size = size;
        this.Price = price;
        this.woodCount = woodCount;
        this.stoneCount = stoneCount;
    }

    public AnimalHouseType getBaseType() {
        return switch (this) {
            case DELUXE_BARN, BIG_BARN -> BARN;
            case DELUXE_COOP, BIG_COOP -> COOP;
            default -> this;
        };
    }

    public int getCapacity() {
        return capacity;
    }

    public Size getSize() {
        return size;
    }

    public int getPrice() {
        return Price;
    }

    public int getWoodCount() {
        return woodCount;
    }

    public int getStoneCount() {
        return stoneCount;
    }
}
