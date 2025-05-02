package models.enums;

import models.TileContent;
import models.dateTime.Season;
import java.util.Random;

public enum Trees implements TileContent {
    APRICOT_TREE("Apricot Tree", "Apricot Sapling", "7-7-7-7", 28, "Apricot", 1, 59, true, 38, Season.SPRING, "🟡"),
    CHERRY_TREE("Cherry Tree", "Cherry Sapling", "7-7-7-7", 28, "Cherry", 1, 80, true, 38, Season.SPRING, "🍒"),
    BANANA_TREE("Banana Tree", "Banana Sapling", "7-7-7-7", 28, "Banana", 1, 150, true, 75, Season.SUMMER, "🍌"),
    MANGO_TREE("Mango Tree", "Mango Sapling", "7-7-7-7", 28, "Mango", 1, 130, true, 100, Season.SUMMER, "🥭"),
    ORANGE_TREE("Orange Tree", "Orange Sapling", "7-7-7-7", 28, "Orange", 1, 100, true, 38, Season.SUMMER, "🍊"),
    PEACH_TREE("Peach Tree", "Peach Sapling", "7-7-7-7", 28, "Peach", 1, 140, true, 38, Season.SUMMER, "🍑"),
    APPLE_TREE("Apple Tree", "Apple Sapling", "7-7-7-7", 28, "Apple", 1, 100, true, 38, Season.FALL, "🍏"),
    POMEGRANATE_TREE("Pomegranate Tree", "Pomegranate Sapling", "7-7-7-7", 28, "Pomegranate", 1, 140, true, 38, Season.FALL, "🍎"),
    OAK_TREE("Oak Tree", "Acorns", "7-7-7-7", 28, "Oak Resin", 7, 150, false, 0, Season.SPECIAL, "🌳"),
    MAPLE_TREE("Maple Tree", "Maple Seeds", "7-7-7-7", 28, "Maple Syrup", 9, 200, false, 0, Season.SPECIAL, "🍁"),
    PINE_TREE("Pine Tree", "Pine Cones", "7-7-7-7", 28, "Pine Tar", 5, 100, false, 0, Season.SPECIAL, "🌲"),
    MAHOGANY_TREE("Mahogany Tree", "Mahogany Seeds", "7-7-7-7", 28, "Sap", 1, 2, true, -2, Season.SPECIAL, "🌴"),
    MUSHROOM_TREE("Mushroom Tree", "Mushroom Tree Seeds", "7-7-7-7", 28, "Common Mushroom", 1, 40, true, 38, Season.SPECIAL, "🍄"),
    MYSTIC_TREE("Mystic Tree", "Mystic Tree Seeds", "7-7-7-7", 28, "Mystic Syrup", 7, 1000, true, 500, Season.SPECIAL, "🎄"),;

    private final String name;
    private final String source;
    private final String stages;
    private final int totalHarvestTime;
    private final String fruit;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isFruitEdible;
    private final int fruitEnergy;
    private final Season season;
    private final String symbol;

    Trees(String name, String source, String stages, int totalHarvestTime, String fruit, int fruitHarvestCycle,
             int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy, Season season, String symbol) {
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

    public static Trees randomTree() {
        Trees[] values = Trees.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getSymbol() {
        return symbol;
    }
}
