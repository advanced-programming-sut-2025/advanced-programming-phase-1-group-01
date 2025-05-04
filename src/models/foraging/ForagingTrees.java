package models.foraging;

import models.building.TileObject;
import models.dateTime.Season;

import java.util.Random;

public enum ForagingTrees implements TileObject {
    ACORNS(Season.SPECIAL, "🌳"),
    MAPLE_SEEDS(Season.SPECIAL, "🍁"),
    PINE_CONS(Season.SPECIAL, "🌲"),
    MAHOGANY_SEEDS(Season.SPECIAL, "🌴"),
    MUSHROOMS_TREE_SEEDS(Season.SPECIAL, "🍄"),;

    private Season season;
    private final String symbol;

    ForagingTrees(Season season, String symbol) {
        this.season = season;
        this.symbol = symbol;
    }

    private static final Random RANDOM = new Random();

    public static ForagingTrees randomForagingTree() {
        ForagingTrees[] values = ForagingTrees.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getSymbol() {
        return symbol;
    }
}
