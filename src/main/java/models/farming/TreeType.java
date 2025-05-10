package models.farming;

import models.building.TileObject;
import models.dateTime.Season;

import java.util.Random;

public enum TreeType implements TileObject, FarmingEnum {
    APRICOT_TREE("Apricot Tree", "Apricot new int[]Sapling", new int[]{1, 1, 1, 1}, 28, "Apricot", 1, 59, true, 38, Season.SPRING, "🟡"),
    CHERRY_TREE("Cherry Tree", "Cherry Sapling", new int[]{1, 1, 1, 1}, 28, "Cherry", 1, 80, true, 38, Season.SPRING, "🍒"),
    BANANA_TREE("Banana Tree", "Banana Sapling", new int[]{1, 1, 1, 1}, 28, "Banana", 1, 150, true, 75, Season.SUMMER, "🍌"),
    MANGO_TREE("Mango Tree", "Mango Sapling", new int[]{1, 1, 1, 1}, 28, "Mango", 1, 130, true, 100, Season.SUMMER, "🥭"),
    ORANGE_TREE("Orange Tree", "Orange Sapling", new int[]{1, 1, 1, 1}, 28, "Orange", 1, 100, true, 38, Season.SUMMER, "🍊"),
    PEACH_TREE("Peach Tree", "Peach Sapling", new int[]{1, 1, 1, 1}, 28, "Peach", 1, 140, true, 38, Season.SUMMER, "🍑"),
    APPLE_TREE("Apple Tree", "Apple Sapling", new int[]{1, 1, 1, 1}, 28, "Apple", 1, 100, true, 38, Season.FALL, "🍏"),
    POMEGRANATE_TREE("Pomegranate Tree", "Pomegranate Sapling", new int[]{1, 1, 1, 1}, 28, "Pomegranate", 1, 140, true, 38, Season.FALL, "🍎"),
    OAK_TREE("Oak Tree", "Acorns", new int[]{1, 1, 1, 1}, 28, "Oak Resin", 7, 150, false, 0, Season.SPECIAL, "🌳"),
    MAPLE_TREE("Maple Tree", "Maple Seeds", new int[]{1, 1, 1, 1}, 28, "Maple Syrup", 9, 200, false, 0, Season.SPECIAL, "🍁"),
    PINE_TREE("Pine Tree", "Pine Cones", new int[]{1, 1, 1, 1}, 28, "Pine Tar", 5, 100, false, 0, Season.SPECIAL, "🌲"),
    MAHOGANY_TREE("Mahogany Tree", "Mahogany Seeds", new int[]{1, 1, 1, 1}, 28, "Sap", 1, 2, true, -2, Season.SPECIAL, "🌴"),
    MUSHROOM_TREE("Mushroom Tree", "Mushroom Tree Seeds", new int[]{1, 1, 1, 1}, 28, "Common Mushroom", 1, 40, true, 38, Season.SPECIAL, "🍄"),
    MYSTIC_TREE("Mystic Tree", "Mystic Tree Seeds", new int[]{1, 1, 1, 1}, 28, "Mystic Syrup", 7, 1000, true, 500, Season.SPECIAL, "🎄"),
    ;

    private final String name;
    private final String source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final String fruit;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isFruitEdible;
    private final int fruitEnergy;
    private final Season season;
    private final String symbol;

    TreeType(String name, String source, int[] stages, int totalHarvestTime, String fruit, int fruitHarvestCycle, int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy, Season season, String symbol) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruit = fruit;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.season = season;
        this.symbol = symbol;
    }

    private static final Random RANDOM = new Random();

    public static TreeType randomTree() {
        TreeType[] values = TreeType.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public String getFruit() {
        return fruit;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public int getFruitBaseSellPrice() {
        return fruitBaseSellPrice;
    }

    public boolean isFruitEdible() {
        return isFruitEdible;
    }

    public int getFruitEnergy() {
        return fruitEnergy;
    }

    public Season getSeason() {
        return season;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TreeType fromString(String name) {
        for (TreeType treeType : values()) {
            if (treeType.name.equalsIgnoreCase(name)) {
                return treeType;
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
                Fruit: %s
                Fruit Harvest Cycle: %d
                Fruit Base Sell Price: %d
                Is Fruit Edible: %B
                Fruit Energy: %d
                Season: %s""".formatted(name, source, stages, totalHarvestTime, fruit, fruitHarvestCycle, fruitBaseSellPrice, isFruitEdible, fruitEnergy, season);
    }
}