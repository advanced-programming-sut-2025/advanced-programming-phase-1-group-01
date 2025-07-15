package com.stardew_valley.models.shop.enums;

public enum FishShopProducts {
    FISH_SMOKER_RECIPE("Fish Smoker (Recipe)", 10000, -1, 1),
    TROUT_SOUP("trout Soup", 250, -1, 1),
    BAMBOO_POLE("bamboo pole", 500, -1, 1),
    TRAINING_ROD("training rod", 25, -1, 1),
    FIBERGLASS_ROD("fiberglass rod", 1800, 2, 1),
    IRIDIUM_ROD("iridium rod", 7500, 4, 1);

    private final String name;
    private final int price;
    private final int fishingSkillRequired;
    private final int dailyLimit;

    FishShopProducts(String name, int price, int fishingSkillRequired, int dailyLimit) {
        this.name = name;
        this.price = price;
        this.fishingSkillRequired = fishingSkillRequired;
        this.dailyLimit = dailyLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getFishingSkillRequired() {
        return fishingSkillRequired;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
