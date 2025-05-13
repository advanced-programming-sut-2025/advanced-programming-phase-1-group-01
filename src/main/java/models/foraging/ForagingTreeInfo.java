package models.foraging;

import models.building.TileObject;
import models.dateTime.Season;
import models.farming.FarmingEnum;

import java.util.Random;

public enum ForagingTreeInfo implements TileObject, FarmingEnum {
    ACORNS("Acorns", Season.SPECIAL, "🌳"),
    MAPLE_SEEDS("Maple Seeds", Season.SPECIAL, "🍁"),
    PINE_CONS("Pine Cons", Season.SPECIAL, "🌲"),
    MAHOGANY_SEEDS("Mahogany Seeds", Season.SPECIAL, "🌴"),
    MUSHROOMS_TREE_SEEDS("Mushroom Tree Seeds", Season.SPECIAL, "🍄"),;

    private final String name;
    private final Season season;
    private final String symbol;

    ForagingTreeInfo(String name, Season season, String symbol) {
        this.name = name;
        this.season = season;
        this.symbol = symbol;
    }

    private static final Random RANDOM = new Random();

    public static ForagingTreeInfo randomForagingTree() {
        ForagingTreeInfo[] values = ForagingTreeInfo.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }

    public String getSymbol() {
        return symbol;
    }

    public static ForagingTreeInfo fromString(String string) {
        for (ForagingTreeInfo foragingTreeInfo : values()) {
            if (foragingTreeInfo.name.equals(string)) {
                return foragingTreeInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return """
                Name: %s
                Season: %s  """;
    }
}
