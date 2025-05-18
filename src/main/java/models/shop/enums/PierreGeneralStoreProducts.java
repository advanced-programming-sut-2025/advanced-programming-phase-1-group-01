package models.shop.enums;

import models.dateTime.Season;
import models.shop.PierreItem;

public enum PierreGeneralStoreProducts {
    RICE("Rice", -1, Season.SPECIAL, 200, 200),
    WHEAT_FLOUR("Wheat Flour", -1, Season.SPECIAL, 100, 100),
    BOUQUET("Bouquet", 2, Season.SPECIAL, 1000, 1000),
    WEDDING_RING("Wedding Ring", 2, Season.SPECIAL, 10000, 10000),
    DEHYDRATOR_RECIPE("Dehydrator (Recipe)", 1, Season.SPECIAL, 10000, 10000),
    GRASS_STARTER_RECIPE("Grass Starter (Recipe)", 1, Season.SPECIAL, 1000, 1000),
    SUGAR("Sugar", -1, Season.SPECIAL, 100, 100),
    OIL("Oil", -1, Season.SPECIAL, 200, 200),
    VINEGAR("Vinegar", -1, Season.SPECIAL, 200, 200),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", -1, Season.SPECIAL, 150, 150),
    GRASS_STARTER("Grass Starter", -1, Season.SPECIAL, 100, 100),
    SPEED_GRO("Speed-Gro", -1, Season.SPECIAL, 100, 100),
    APPLE_SAPLING("Apple Sapling", -1, Season.SPECIAL, 4000, 4000),
    APRICOT_SAPLING("Apricot Sapling", -1, Season.SPECIAL, 2000, 2000),
    CHERRY_SAPLING("Cherry Sapling", -1, Season.SPECIAL, 3400, 3400),
    ORANGE_SAPLING("Orange Sapling", -1, Season.SPECIAL, 4000, 4000),
    PEACH_SAPLING("Peach Sapling", -1, Season.SPECIAL, 6000, 6000),
    POMEGRANATE_SAPLING("Pomegranate Sapling", -1, Season.SPECIAL, 6000, 6000),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", -1, Season.SPECIAL, 100, 100),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", -1, Season.SPECIAL, 150, 150),

    // Spring
    PARSNIP_SEEDS("Parsnip Seeds", 5, Season.SPRING, 20, 30),
    BEAN_STARTER("Bean Starter", 5, Season.SPRING, 60, 90),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", 5, Season.SPRING, 80, 120),
    POTATO_SEEDS("Potato Seeds", 5, Season.SPRING, 50, 75),
    TULIP_BULB("Tulip Bulb", 5, Season.SPRING, 20, 30),
    KALE_SEEDS("Kale Seeds", 5, Season.SPRING, 70, 105),
    JAZZ_SEEDS("Jazz Seeds", 5, Season.SPRING, 30, 45),
    GARLIC_SEEDS("Garlic Seeds", 5, Season.SPRING, 40, 60),
    RICE_SHOOT("Rice Shoot", 5, Season.SPRING, 40, 60),

    // Summer
    MELON_SEEDS("Melon Seeds", 5, Season.SUMMER, 80, 120),
    TOMATO_SEEDS("Tomato Seeds", 5, Season.SUMMER, 50, 75),
    BLUEBERRY_SEEDS("Blueberry Seeds", 5, Season.SUMMER, 80, 120),
    PEPPER_SEEDS("Pepper Seeds", 5, Season.SUMMER, 40, 60),
    WHEAT_SEEDS_SUMMER("Wheat Seeds", 5, Season.SUMMER, 10, 15),
    RADISH_SEEDS("Radish Seeds", 5, Season.SUMMER, 40, 60),
    POPPY_SEEDS("Poppy Seeds", 5, Season.SUMMER, 100, 150),
    SPANGLE_SEEDS("Spangle Seeds", 5, Season.SUMMER, 50, 75),
    HOPS_STARTER("Hops Starter", 5, Season.SUMMER, 60, 90),
    CORN_SEEDS_SUMMER("Corn Seeds", 5, Season.SUMMER, 150, 225),
    SUNFLOWER_SEEDS_SUMMER("Sunflower Seeds", 5, Season.SUMMER, 200, 300),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", 5, Season.SUMMER, 100, 150),

    // Fall
    EGGPLANT_SEEDS("Eggplant Seeds", 5, Season.FALL, 20, 30),
    CORN_SEEDS_FALL("Corn Seeds", 5, Season.FALL, 150, 225),
    PUMPKIN_SEEDS("Pumpkin Seeds", 5, Season.FALL, 100, 150),
    BOK_CHOY_SEEDS("Bok Choy Seeds", 5, Season.FALL, 50, 75),
    YAM_SEEDS("Yam Seeds", 5, Season.FALL, 60, 90),
    CRANBERRY_SEEDS("Cranberry Seeds", 5, Season.FALL, 240, 360),
    SUNFLOWER_SEEDS_FALL("Sunflower Seeds", 5, Season.FALL, 200, 300),
    FAIRY_SEEDS("Fairy Seeds", 5, Season.FALL, 200, 300),
    AMARANTH_SEEDS("Amaranth Seeds", 5, Season.FALL, 70, 105),
    GRAPE_STARTER("Grape Starter", 5, Season.FALL, 60, 90),
    WHEAT_SEEDS_FALL("Wheat Seeds", 5, Season.FALL, 10, 15),
    ARTICHOKE_SEEDS("Artichoke Seeds", 5, Season.FALL, 30, 45);

    private final String name;
    private final int dailyLimit;
    private final Season season;
    private final int inSeasonPrice;
    private final int offSeasonPrice;

    PierreGeneralStoreProducts(String name, int dailyLimit, Season season, int inSeasonPrice, int offSeasonPrice) {
        this.name = name;
        this.dailyLimit = dailyLimit;
        this.season = season;
        this.inSeasonPrice = inSeasonPrice;
        this.offSeasonPrice = offSeasonPrice;
    }

    public String getName() { return name; }
    public int getDailyLimit() { return dailyLimit; }
    public Season getSeason() { return season; }
    public int getPrice(Season season) {
        if (this.season == season) {
            return inSeasonPrice;
        }
        return offSeasonPrice;
    }

    public PierreItem toItem() {
        return new PierreItem(name, season, inSeasonPrice, offSeasonPrice);
    }
}

