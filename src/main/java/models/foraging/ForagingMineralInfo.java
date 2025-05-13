package models.foraging;

import models.building.TileObject;

import java.util.Random;

public enum ForagingMineralInfo implements TileObject {
    QUARTZ("A clear crystal commonly found in caves and mines.", 25, "💎"),
    EARTH_CRISTAL("A resinous substance found near the surface.", 50, "🪨"),
    FROZEN_TEAR("A crystal fabled to be the frozen tears of a yeti.", 75, "🗿"),
    FIRE_QUARTZ("A glowing red crystal commonly found near hot lava.", 100, "🌋"),
    EMERALD("A precious stone with a brilliant green color.", 250, "💚"),
    AQUAMARINE("A shimmery blue-green gem.", 180, "💙"),
    RUBY("A precious stone that is sought after for its rich color and beautiful luster.", 250, "❤️"),
    AMETHYST("A purple variant of quartz.", 100, "🟣"),
    TOPAZ("Fairly common but still prized for its beauty.", 80, "💛"),
    JADE("A pale green ornamental stone.", 200, "💚"),
    DIAMOND("A rare and valuable gem.", 750, "💎"),
    PRISMATIC_SHARD("A very rare and powerful substance with unknown origins.", 2000, "🔮"),
    COPPER("A common ore that can be smelted into bars.", 5, "🪨"),
    IRON("A fairly common ore that can be smelted into bars.", 10, "🥄"),
    GOLD("A precious ore that can be smelted into bars.", 25, "💰"),
    IRIDIUM("An exotic ore with many curious properties. Can be smelted into bars.", 100, "💎"),
    COAL("A combustible rock that is useful for crafting and smelting.", 15, "🪨"),;

    private final String description;
    private final int sellPrice;
    private final String symbol ;

    ForagingMineralInfo(String description, int sellPrice, String symbol) {
        this.description = description;
        this.sellPrice = sellPrice;
        this.symbol = symbol;
    }

    private static final Random RANDOM = new Random();

    public static ForagingMineralInfo randomForagingMineral() {
        ForagingMineralInfo[] values = ForagingMineralInfo.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return switch (this) {
            case QUARTZ -> "Quartz";
            case EARTH_CRISTAL -> "Earth Cristal";
            case FROZEN_TEAR -> "Frozen Tear";
            case FIRE_QUARTZ -> "Fire Quartz";
            case EMERALD -> "Emerald";
            case AQUAMARINE -> "Aquamarine";
            case RUBY -> "Ruby";
            case AMETHYST -> "Amethyst";
            case TOPAZ -> "Topaz";
            case JADE -> "Jade";
            case DIAMOND -> "Diamond";
            case PRISMATIC_SHARD -> "Prismatic Shard";
            case COPPER -> "Copper";
            case IRON -> "Iron";
            case GOLD -> "Gold";
            case IRIDIUM -> "Iridium";
            case COAL -> "Coal";
        };
    }

    public String getName() {
        return toString();
    }
}
