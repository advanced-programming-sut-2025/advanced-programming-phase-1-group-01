package models.enums;

public enum ForagingCrops {
    COMMON_MUSHROOM(Season.SPECIAL, 40, 38),
    DAFFODIL(Season.SPRING, 30, 0),
    DANDELION(Season.SPRING, 40, 25),
    LEEK(Season.SPRING, 60, 40),
    MOREL(Season.SPRING, 150, 20),
    SALMONBERRY(Season.SPRING, 5, 25),
    SPRING_ONION(Season.SPRING, 8, 13),
    WILD_HORSERADISH(Season.SPRING, 50, 13),
    FIDDLEHEAD_FERN(Season.SUMMER, 90, 25),
    GRAPE(Season.SUMMER, 80, 38),
    RED_MUSHROOM(Season.SUMMER, 75, -50),
    SPICE_BERRY(Season.SUMMER, 80, 25),
    SWEET_PEA(Season.SUMMER, 50, 0),
    BLACKBERRY(Season.FALL, 25, 25),
    CHANTERELLE(Season.FALL, 160, 75),
    HAZELNUT(Season.FALL, 40, 38),
    PURPLE_MUSHROOM(Season.FALL, 90, 30),
    WILD_PLUM(Season.FALL, 80, 25),
    CROCUS(Season.WINTER, 60, 0),
    CRISTAL_FRUIT(Season.WINTER, 150, 63),
    HOLLY(Season.WINTER, 80, -37),
    SNOW_YAM(Season.WINTER, 100, 30),
    WINTER_ROOT(Season.WINTER, 70, 25);

    private Season season;
    private int baseSellPrice;
    private double energy;

    ForagingCrops(Season season, int baseSellPrice, double energy) {
        this.season = season;
        this.baseSellPrice = baseSellPrice;
        this.energy = energy;
    }
}
