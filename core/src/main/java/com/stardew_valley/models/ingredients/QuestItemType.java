package com.stardew_valley.models.ingredients;

public enum QuestItemType {
    IRON_BAR("iron bar", 100),
    COPPER_BAR("copper bar", 200),
    GOLD_BAR("gold bar", 300),
    SILVER_BAR("silver bar", 400),
    IRIDIUM_BAR("iridium bar", 500),
    COPPER_OAR("copper oar", 50),
    GOLD_OAR("gold oar", 60),
    SILVER_OAR("silver oar", 70),
    IRON_OAR("iron oar", 80),
    IRIDIUM_OAR("iridium oar", 90),
    PUMPKIN_PIE("pumpkin pie", 50),
    STONE("stone", 80),
    IRON_GOLD("iron gold", 70),
    PUMPKIN("pumpkin", 60),
    WHEAT("wheat", 20),
    CORN("corn", 60),
    HOPS("hops", 50),
    GARLIC("garlic", 40),
    CARROT("carrot", 70),
    MILK("milk", 30),
    BIG_MILK("big milk", 50),
    GOAT_MILK("goat milk", 60),
    BIG_GOAT_MILK("big goat milk", 80),
    SHEEP_WOOL("sheep wool", 100),
    TRUFFLE("truffle", 300),
    FIBER("fiber",500),
    ACORN("acorn", 60),
    MAPLE_SEED("maple seed", 50),
    PINE_CONE("pine cone", 150),
    MAHOGANY("mahogany", 200);


    private final String name;
    private final int price;

    QuestItemType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public QuestItem toItem() {
        return new QuestItem(this);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
