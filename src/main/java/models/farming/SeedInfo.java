package models.farming;

import models.dateTime.Season;

import java.util.Random;

public enum SeedInfo {
    JAZZ_SEEDS("Jazz Seeds", Season.SPRING),
    CARROT_SEEDS("Carrot Seeds", Season.SPRING),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", Season.SPRING),
    COFFEE_BEAN("Coffee Bean", Season.SPRING),
    GARLIC_SEEDS("Garlic Seeds", Season.SPRING),
    BEAN_STARTER("Bean Starter", Season.SPRING),
    KALE_SEEDS("Kale Seeds", Season.SPRING),
    PARSNIP_SEEDS("Parsnip Seeds", Season.SPRING),
    POTATO_SEEDS("Potato Seeds", Season.SPRING),
    RHUBARB_SEEDS("Rhubarb Seeds", Season.SPRING),
    STRAWBERRY_SEEDS("Strawberry Seeds", Season.SPRING),
    TULIP_BULB("Tulip Bulb", Season.SPRING),
    RICE_SHOOT("Rice Shoot", Season.SPRING),
    BLUEBERRY_SEEDS("Blueberry Seeds", Season.SUMMER),
    CORN_SEEDS("Corn Seeds", Season.SUMMER),
    HOPS_STARTER("Hops Starter", Season.SUMMER),
    PEPPER_SEEDS("Pepper Seeds", Season.SUMMER),
    MELON_SEEDS("Melon Seeds", Season.SUMMER),
    POPPY_SEEDS("Poppy Seeds", Season.SUMMER),
    RADISH_SEEDS("Radish Seeds", Season.SUMMER),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", Season.SUMMER),
    STARFRUIT_SEEDS("Starfruit Seeds", Season.SUMMER),
    SPANGLE_SEEDS("Spangle Seeds", Season.SUMMER),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", Season.SUMMER),
    SUNFLOWER_SEEDS("Sunflower Seeds", Season.SUMMER),
    TOMATO_SEEDS("Tomato Seeds", Season.SUMMER),
    WHEAT_SEEDS("Wheat Seeds", Season.SUMMER),
    AMARANTH_SEEDS("Amaranth Seeds", Season.FALL),
    ARTICHOKE_SEEDS("Artichoke Seeds", Season.FALL),
    BEET_SEEDS("Beet Seeds", Season.FALL),
    BOKCHOY_SEEDS("Bokchoy Seeds", Season.FALL),
    BROCCOLI_SEEDS("Broccoli Seeds", Season.FALL),
    CRANBERRY_SEEDS("Cranberry Seeds", Season.FALL),
    EGGPLANT_SEEDS("Eggplant Seeds", Season.FALL),
    FAIRY_SEEDS("Fairy Seeds", Season.FALL),
    GRAPE_STARTER("Grape Starter", Season.FALL),
    PUMPKIN_SEEDS("Pumpkin Seeds", Season.FALL),
    YAM_SEEDS("Yam Seeds", Season.FALL),
    RARE_SEEDS("Rare Seeds", Season.FALL),
    POWDERMELON_SEEDS("Powdermelon Seeds", Season.WINTER),
    ANCIENT_SEEDS("Ancient Seeds", Season.SPECIAL),
    MIXED_SEEDS("Mixed Seeds", Season.SPECIAL);

    private final String name;
    private final Season season;

    SeedInfo(String name, Season season) {
        this.name = name;
        this.season = season;
    }

    private static final Random RANDOM = new Random();

    public static SeedInfo randomForagingSeed() {
        SeedInfo[] seedInfos = SeedInfo.values();
        return seedInfos[RANDOM.nextInt(seedInfos.length)];
    }

    public Season getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }

    public static SeedInfo fromString(String name) {
        for (SeedInfo seedInfo : SeedInfo.values()) {
            if (seedInfo.getName().equalsIgnoreCase(name)) {
                return seedInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
