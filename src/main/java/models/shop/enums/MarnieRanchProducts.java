package models.shop.enums;

import models.shop.MarnieRanchItem;

public enum MarnieRanchProducts {
    HAY("hay", 50, -1),
    MILK_PAIL("milk pail", 1000, 1),
    SHEARS("shears", 1000, 1);

    private final String name;
    private final int price;
    private final int dailyLimit;

    MarnieRanchProducts(String name, int price, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public MarnieRanchItem toItem() {
        return new MarnieRanchItem(name,price);
    }
}


