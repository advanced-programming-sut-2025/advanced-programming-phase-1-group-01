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
    MAHOGANY("mahogany", 200),
    EGG("egg", 50),
    SARDINE("sardine", 40),
    SALMON("salmon", 75),
    LEEK("leek", 60),
    DANDELION("dandelion", 40),
    WHEAT_FLOUR("wheat flour", 100),
    SUGAR("sugar", 100),
    TOMATO("tomato", 60),
    CHEESE("cheese", 230),
    ANY_FISH("any fish", 60),
    RICE("rice", 100),
    COFFEE("coffee", 150),
    POTATO("potato", 80),
    OIL("oil", 200),
    BLUEBERRY("blueberry", 50),
    MELON("melon", 250),
    APRICOT("apricot", 50),
    RED_CABBAGE("red cabbage", 260),
    RADISH("radish", 90),
    AMARANTH("amaranth", 150),
    KALE("kale", 110),
    BEET("beet", 100),
    OMELET("omelet", 125),
    PARSNIP("parsnip", 35),
    BREAD("bread", 120),
    EGGPLANT("eggplant", 60),
    HASH_BROWNS("hash browns", 120),
    FLOUNDER("flounder", 100),
    MIDNIGHT_CARP("midnight carp", 150);


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
