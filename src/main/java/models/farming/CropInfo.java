package models.farming;

import models.dateTime.Season;

import java.util.List;
import java.util.stream.Collectors;

// this enum contains all kinds of crops
public enum CropInfo implements FarmingEnum {
    BLUE_JAZZ("Blue Jazz", SeedInfo.JAZZ_SEEDS, new int[]{1, 2, 2, 2}, 7, true, -1, 50, true, 45, List.of(Season.SPRING), false),
    CARROT("Carrot", SeedInfo.CARROT_SEEDS, new int[]{1, 1, 1}, 3, true, -1, 35, true, 75, List.of(Season.SPRING), false),
    CAULIFLOWER("Cauliflower", SeedInfo.CAULIFLOWER_SEEDS, new int[]{1, 2, 4, 4, 1}, 12, true, -1, 175, true, 75, List.of(Season.SPRING), true),
    COFFEE_BEAN("Coffee Bean", SeedInfo.COFFEE_BEAN, new int[]{1, 2, 2, 3, 2}, 10, false, 2, 15, false, 0, List.of(Season.SPRING, Season.SUMMER), false),
    GARLIC("Garlic", SeedInfo.GARLIC_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 60, true, 20, List.of(Season.SPRING), false),
    GREEN_BEAN("Green Bean", SeedInfo.BEAN_STARTER, new int[]{1, 1, 1, 3, 4}, 10, false, 3, 40, true, 25, List.of(Season.SPRING), false),
    KALE("Kale", SeedInfo.KALE_SEEDS, new int[]{1, 2, 2, 1}, 6, true, -1, 110, true, 50, List.of(Season.SPRING), false),
    PARSNIP("Parsnip", SeedInfo.PARSNIP_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 35, true, 25, List.of(Season.SPRING), false),
    POTATO("Potato", SeedInfo.POTATO_SEEDS, new int[]{1, 1, 1, 2, 1}, 6, true, -1, 80, true, 25, List.of(Season.SPRING), false),
    RHUBARB("Rhubarb", SeedInfo.RHUBARB_SEEDS, new int[]{2, 2, 2, 3, 4}, 13, true, -1, 220, false, 0, List.of(Season.SPRING), false),
    STRAWBERRY("Strawberry", SeedInfo.STRAWBERRY_SEEDS, new int[]{1, 1, 2, 2, 2}, 8, false, 4, 120, true, 50, List.of(Season.SPRING), false),
    TULIP("Tulip", SeedInfo.TULIP_BULB, new int[]{1, 1, 2, 2}, 6, true, -1, 30, true, 45, List.of(Season.SPRING), false),
    UNMILLED_RICE("Unmilled Rice", SeedInfo.RICE_SHOOT, new int[]{1, 2, 2, 3}, 8, true, -1, 30, true, 3, List.of(Season.SPRING), false),
    BLUEBERRY("Blueberry", SeedInfo.BLUEBERRY_SEEDS, new int[]{1, 3, 3, 4, 2}, 13, false, 4, 50, true, 25, List.of(Season.SPRING), false),
    CORN("Corn", SeedInfo.CORN_SEEDS, new int[]{2, 3, 3, 3, 3}, 14, false, 4, 50, true, 25, List.of(Season.SPRING, Season.FALL), false),
    HOPS("Hops", SeedInfo.HOPS_STARTER, new int[]{1, 1, 2, 3, 4}, 11, false, 1, 25, true, 45, List.of(Season.SUMMER), false),
    HOT_PEPPER("Hot Pepper", SeedInfo.PEPPER_SEEDS, new int[]{1, 1, 1, 1, 1}, 5, false, 3, 40, true, 13, List.of(Season.SUMMER), false),
    MELON("Melon", SeedInfo.MELON_SEEDS, new int[]{1, 2, 3, 3, 3}, 12, true, -1, 250, true, 113, List.of(Season.SUMMER), true),
    POPPY("Poppy", SeedInfo.POPPY_SEEDS, new int[]{1, 2, 2, 2}, 7, true, -1, 140, true, 45, List.of(Season.SUMMER), false),
    RADISH("Radish", SeedInfo.RADISH_SEEDS, new int[]{2, 1, 2, 1}, 6, true, -1, 90, true, 45, List.of(Season.SUMMER), false),
    RED_CABBAGE("Red Cabbage", SeedInfo.RED_CABBAGE_SEEDS, new int[]{2, 1, 2, 2, 2}, 9, true, -1, 260, true, 75, List.of(Season.SUMMER), false),
    STARFRUIT("Starfruit", SeedInfo.STARFRUIT_SEEDS, new int[]{2, 3, 2, 3, 3}, 13, true, -1, 750, true, 125, List.of(Season.SUMMER), false),
    SUMMER_SPANGLE("Summer Spangle", SeedInfo.SPANGLE_SEEDS, new int[]{1, 2, 3, 1}, 8, true, -1, 90, true, 45, List.of(Season.SUMMER), false),
    SUMMER_SQUASH("Summer Squash", SeedInfo.SUMMER_SQUASH_SEEDS, new int[]{1, 1, 1, 2, 1}, 6, false, 3, 45, true, 63, List.of(Season.SUMMER), false),
    SUNFLOWER("Sunflower", SeedInfo.SUNFLOWER_SEEDS, new int[]{1, 2, 3, 2}, 8, true, -1, 80, true, 45, List.of(Season.SUMMER), false),
    TOMATO("Tomato", SeedInfo.TOMATO_SEEDS, new int[]{2, 2, 2, 2, 3}, 11, false, 4, 60, true, 20, List.of(Season.SUMMER), false),
    WHEAT("Wheat", SeedInfo.WHEAT_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 25, false, 0, List.of(Season.SUMMER, Season.FALL), false),
    AMARANTH("Amaranth", SeedInfo.AMARANTH_SEEDS, new int[]{1, 2, 2, 2}, 7, true, -1, 150, true, 50, List.of(Season.FALL), false),
    ARTICHOKE("Artichoke", SeedInfo.ARTICHOKE_SEEDS, new int[]{2, 2, 1, 2, 1}, 8, true, -1, 160, true, 30, List.of(Season.FALL), false),
    BEET("Beet", SeedInfo.BEET_SEEDS, new int[]{1, 1, 2, 2}, 6, true, -1, 100, true, 30, List.of(Season.FALL), false),
    BOK_CHO("Bok Choy", SeedInfo.BOKCHOY_SEEDS, new int[]{1, 1, 1, 1}, 4, true, -1, 80, true, 25, List.of(Season.FALL), false),
    BROCCOLI("Broccoli", SeedInfo.BROCCOLI_SEEDS, new int[]{2, 2, 2, 2}, 8, false, 4, 70, true, 63, List.of(Season.FALL), false),
    CRANBERRIES("Cranberries", SeedInfo.CRANBERRY_SEEDS, new int[]{1, 2, 1, 1, 2}, 7, false, 5, 75, true, 38, List.of(Season.FALL), false),
    EGGPLANT("Eggplant", SeedInfo.EGGPLANT_SEEDS, new int[]{1, 1, 1, 1}, 5, false, 5, 60, true, 20, List.of(Season.FALL), false),
    FAIRY_ROSE("Fairy Rose", SeedInfo.FAIRY_SEEDS, new int[]{1, 4, 4, 3}, 12, true, -1, 290, true, 45, List.of(Season.FALL), false),
    GRAPE("Grape", SeedInfo.GRAPE_STARTER, new int[]{1, 1, 2, 3, 3}, 10, false, 3, 80, true, 38, List.of(Season.FALL), false),
    PUMPKIN("Pumpkin", SeedInfo.PUMPKIN_SEEDS, new int[]{1, 2, 3, 4, 3}, 13, true, -1, 320, false, 0, List.of(Season.FALL), true),
    YAM("Yam", SeedInfo.YAM_SEEDS, new int[]{1, 3, 3, 3}, 10, true, -1, 160, true, 45, List.of(Season.FALL), false),
    SWEET_GEM_BERRY("Sweet Gem Berry", SeedInfo.RARE_SEEDS, new int[]{2, 4, 6, 6, 6}, 24, true, -1, 3000, false, 0, List.of(Season.FALL), false),
    POWDERMELON("Powdermelon", SeedInfo.POWDERMELON_SEEDS, new int[]{1, 2, 1, 2, 1}, 7, true, -1, 60, true, 63, List.of(Season.WINTER), true),
    ANCIENT_FRUIT("Ancient Fruit", SeedInfo.ANCIENT_SEEDS, new int[]{2, 7, 7, 7, 5}, 28, false, 7, 550, false, 0, List.of(Season.SPRING, Season.SUMMER, Season.FALL), false);

    private final String name;
    private final SeedInfo source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int regrowthTime;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final List<Season> seasons;
    private final boolean canBecomeGiant;

    CropInfo(String name, SeedInfo source, int[] stages, int totalHarvestTime, boolean oneTime, int regrowthTime, int baseSellPrice, boolean isEdible, int energy, List<Season> seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
    }

    public String getName() {
        return name;
    }

    public SeedInfo getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
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

    public List<Season> getSeasons() {
        return seasons;
    }

    public String getSeasonsStr() {
        return seasons.stream().map(Season::toString).collect(Collectors.joining(" & "));
    }

    public boolean canBecomeGiant() {
        return canBecomeGiant;
    }

    public static CropInfo fromSeed(SeedInfo seedInfo) {
        for (CropInfo info : CropInfo.values()) {
            if (info.source == seedInfo) {
                return info;
            }
        }
        return null;
    }

    public static CropInfo fromString(String name) {
        for (CropInfo cropInfo : values()) {
            if (cropInfo.getName().equalsIgnoreCase(name)) {
                return cropInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return """
                Name: %s
                Source: %s
                Stages: %s
                Total Harvest Time: %d
                One Time: %B
                Regrowth Time: %d
                Base Sell Price: %d
                Is Edible: %B
                Base Energy: %d
                Season: %s
                Can Become Giant: %B""".formatted(name,
                source,
                stages,
                totalHarvestTime,
                oneTime,
                regrowthTime,
                baseSellPrice,
                isEdible,
                energy,
                getSeasonsStr(),
                canBecomeGiant);
    }
}
