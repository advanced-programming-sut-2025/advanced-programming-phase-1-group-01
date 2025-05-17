package models.shop.enums;

import models.shop.MarnieRanchItem;

public enum MarnieRanchProducts {
    CHICKEN("Chicken", 800, "Coop", 2),
    COW("Cow", 1500, "Barn", 2),
    GOAT("Goat", 4000, "Big Barn", 2),
    DUCK("Duck", 1200, "Big Coop", 2),
    SHEEP("Sheep", 8000, "Deluxe Barn", 2),
    RABBIT("Rabbit", 8000, "Deluxe Coop", 2),
    DINOSAUR("Dinosaur", 14000, "Big Coop", 2),
    PIG("Pig", 16000, "Deluxe Barn", 2);

    private final String name;
    private final int price;
    private final String buildingRequired;
    private final int dailyLimit;

    MarnieRanchProducts(String name, int price, String buildingRequired, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.buildingRequired = buildingRequired;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getBuildingRequired() {
        return buildingRequired;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public MarnieRanchItem toItem() {
        return new MarnieRanchItem(name,price);
    }
}

