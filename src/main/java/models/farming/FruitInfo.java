package models.farming;

import models.dateTime.Season;

public enum FruitInfo implements FarmingEnum {
    APRICOT("Apricot", 59, true, 38),
    CHERRY("Cherry", 80, true, 38),
    BANANA("Banana", 150, true, 75),
    MANGO("Mango", 130, true, 100),
    ORANGE("Orange", 100, true, 38),
    PEACH("Peach", 140, true, 38),
    APPLE("Apple", 100, true, 38),
    POMEGRANATE("Pomegranate", 140, true, 38),
    OAK_RESIN("Oak Resin", 150, false, 0),
    MAPLE_SYRUP("Maple Syrup", 200, false, 0),
    PINE_TAR("Pine Tar", 100, false, 0),
    SAP("Sap", 2, true, -2),
    COMMON_MUSHROOM("Common Mushroom", 40, true, 38),
    MYSTIC_SYRUP("Mystic Syrup", 1000, true, 500),
    ;

    private final String name;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;

    FruitInfo(String name, int baseSellPrice, boolean isEdible, int energy) {
        this.name = name;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }
}