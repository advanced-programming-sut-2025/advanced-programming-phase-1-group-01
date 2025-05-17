package models.farming;

import models.building.TileObject;
import models.dateTime.Season;
import models.enums.Emoji;
import models.foraging.ForagingTreeInfo;

import java.util.Arrays;
import java.util.Random;

public enum TreeInfo implements FarmingEnum {
    APRICOT_TREE("Apricot Tree", null/*"Apricot Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.APRICOT, 1, Season.SPRING, Emoji.YELLOW_CIRCLE.getSymbol()),
    CHERRY_TREE("Cherry Tree", null/*"Cherry Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.CHERRY, 1, Season.SPRING, Emoji.CHERRIES.getSymbol()),
    BANANA_TREE("Banana Tree", null/*"Banana Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.BANANA, 1, Season.SUMMER, Emoji.BANANA.getSymbol()),
    MANGO_TREE("Mango Tree", null/*"Mango Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.MANGO, 1, Season.SUMMER, Emoji.MANGO.getSymbol()),
    ORANGE_TREE("Orange Tree", null/*"Orange Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.ORANGE, 1, Season.SUMMER, Emoji.ORANGE.getSymbol()),
    PEACH_TREE("Peach Tree", null/*"Peach Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.PEACH, 1, Season.SUMMER, Emoji.PEACH.getSymbol()),
    APPLE_TREE("Apple Tree", null/*"Apple Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.APPLE, 1, Season.FALL, Emoji.GREEN_APPLE.getSymbol()),
    POMEGRANATE_TREE("Pomegranate Tree", null/*"Pomegranate Sapling"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.POMEGRANATE, 1, Season.FALL, Emoji.RED_APPLE.getSymbol()),
    OAK_TREE("Oak Tree", ForagingTreeInfo.ACORNS, new int[]{7, 7, 7, 7}, 28, FruitInfo.OAK_RESIN, 7, Season.SPECIAL, Emoji.DECIDUOUS_TREE.getSymbol()),
    MAPLE_TREE("Maple Tree", ForagingTreeInfo.MAPLE_SEEDS, new int[]{7, 7, 7, 7}, 28, FruitInfo.MAPLE_SYRUP, 9, Season.SPECIAL, Emoji.MAPLE_LEAF.getSymbol()),
    PINE_TREE("Pine Tree", ForagingTreeInfo.PINE_CONES, new int[]{7, 7, 7, 7}, 28, FruitInfo.PINE_TAR, 5, Season.SPECIAL, Emoji.TREE.getSymbol()),
    MAHOGANY_TREE("Mahogany Tree", ForagingTreeInfo.MAHOGANY_SEEDS, new int[]{7, 7, 7, 7}, 28, FruitInfo.SAP, 1, Season.SPECIAL, Emoji.PALM_TREE.getSymbol()),
    MUSHROOM_TREE("Mushroom Tree", ForagingTreeInfo.MUSHROOMS_TREE_SEEDS, new int[]{7, 7, 7, 7}, 28, FruitInfo.COMMON_MUSHROOM, 1, Season.SPECIAL, Emoji.MUSHROOM.getSymbol()),
    MYSTIC_TREE("Mystic Tree", null/*"Mystic Tree Seeds"*/, new int[]{7, 7, 7, 7}, 28, FruitInfo.MYSTIC_SYRUP, 7, Season.SPECIAL, Emoji.CHRISTMAS_TREE.getSymbol()),
    ;

    private final String name;
    private final FarmingEnum source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final FruitInfo fruitInfo;
    private final int fruitHarvestCycle;
    private final Season season;
    private final String symbol;

    TreeInfo(String name, FarmingEnum source, int[] stages, int totalHarvestTime, FruitInfo fruitInfo, int fruitHarvestCycle, Season season, String symbol) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitInfo = fruitInfo;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.season = season;
        this.symbol = symbol;
    }

    private static final Random RANDOM = new Random();

    public static TreeInfo randomTree() {
        TreeInfo[] values = TreeInfo.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getName() {
        return name;
    }

    public FarmingEnum getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public FruitInfo getFruitInfo() {
        return fruitInfo;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public Season getSeason() {
        return season;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TreeInfo fromString(String name) {
        for (TreeInfo treeInfo : values()) {
            if (treeInfo.name.equalsIgnoreCase(name)) {
                return treeInfo;
            }
        }
        return null;
    }

    public static TreeInfo fromTreeSource(TreeSource source) {
        for (TreeInfo treeInfo : values()) {
            if (treeInfo.source == source.getInfo()) {
                return treeInfo;
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
                Season: %s""".formatted(name, source.getName(), Arrays.toString(stages), totalHarvestTime, fruitInfo.getName(), fruitHarvestCycle, fruitInfo.getBaseSellPrice(), fruitInfo.isEdible(), fruitInfo.getEnergy(), season);
    }
}