package com.stardew_valley.models.foraging;

import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.enums.Emoji;
import com.stardew_valley.models.farming.FarmingEnum;

import java.util.Random;

public enum ForagingTreeInfo implements TileObject, FarmingEnum {
    ACORNS("Acorns", Season.SPECIAL, Emoji.DECIDUOUS_TREE.getSymbol()),
    MAPLE_SEEDS("Maple Seeds", Season.SPECIAL, "🍁"),
    PINE_CONES("Pine Cones", Season.SPECIAL, "🌲"),
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

    @Override
    public int getPrice() {
        return 0;
    }

    public Season getSeason() {
        return season;
    }

    public static ForagingTreeInfo fromString(String string) {
        for (ForagingTreeInfo foragingTreeInfo : values()) {
            if (foragingTreeInfo.name.equalsIgnoreCase(string)) {
                return foragingTreeInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public ForagingTree toItem() {
        return new ForagingTree(this);
    }
}
