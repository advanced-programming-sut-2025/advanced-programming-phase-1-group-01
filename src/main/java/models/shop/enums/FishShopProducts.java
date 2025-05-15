package models.shop.enums;

public enum FishShopProducts {
    FISH_SMOKER_RECIPE("Fish Smoker (Recipe)", 10000, -1, 1),
    TROUT_SOUP("Trout Soup", 250, -1, 1),
    BAMBOO_POLE("Bamboo Pole", 500, -1, 1),
    TRAINING_ROD("Training Rod", 25, -1, 1),
    FIBERGLASS_ROD("Fiberglass Rod", 1800, 2, 1),
    IRIDIUM_ROD("Iridium Rod", 7500, 4, 1);

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
