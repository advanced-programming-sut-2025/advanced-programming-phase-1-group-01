package com.stardew_valley.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.farming.FarmingEnum;

import java.util.Random;

public enum ForagingMineralInfo implements FarmingEnum {
    QUARTZ("A clear crystal commonly found in caves and mines.", 25, AssetManager.getAssetManager().getQuartz()),
    EARTH_CRISTAL("A resinous substance found near the surface.", 50, AssetManager.getAssetManager().getEarthCrystal()),
    FROZEN_TEAR("A crystal fabled to be the frozen tears of a yeti.", 75, AssetManager.getAssetManager().getFrozenTear()),
    FIRE_QUARTZ("A glowing red crystal commonly found near hot lava.", 100, AssetManager.getAssetManager().getFireQuartz()),
    EMERALD("A precious stone with a brilliant green color.", 250, AssetManager.getAssetManager().getEmerald()),
    AQUAMARINE("A shimmery blue-green gem.", 180, AssetManager.getAssetManager().getAquamarine()),
    RUBY("A precious stone that is sought after for its rich color and beautiful luster.", 250, AssetManager.getAssetManager().getRuby()),
    AMETHYST("A purple variant of quartz.", 100, AssetManager.getAssetManager().getAmethyst()),
    TOPAZ("Fairly common but still prized for its beauty.", 80, AssetManager.getAssetManager().getTopaz()),
    JADE("A pale green ornamental stone.", 200, AssetManager.getAssetManager().getJade()),
    DIAMOND("A rare and valuable gem.", 750, AssetManager.getAssetManager().getDiamond()),
    PRISMATIC_SHARD("A very rare and powerful substance with unknown origins.", 2000, AssetManager.getAssetManager().getPrismaticShard()),
    COPPER("A common ore that can be smelted into bars.", 5, AssetManager.getAssetManager().getCopper()),
    IRON("A fairly common ore that can be smelted into bars.", 10, AssetManager.getAssetManager().getIron()),
    GOLD("A precious ore that can be smelted into bars.", 25, AssetManager.getAssetManager().getGold()),
    IRIDIUM("An exotic ore with many curious properties. Can be smelted into bars.", 100, AssetManager.getAssetManager().getIridium()),
    COAL("A combustible rock that is useful for crafting and smelting.", 15, AssetManager.getAssetManager().getCoal()),;

    private final String description;
    private final int sellPrice;
    private final Texture texture;

    ForagingMineralInfo(String description, int sellPrice, Texture texture) {
        this.description = description;
        this.sellPrice = sellPrice;
        this.texture = texture;
    }

    private static final Random RANDOM = new Random();

    public static ForagingMineralInfo randomForagingMineral() {
        ForagingMineralInfo[] values = ForagingMineralInfo.values();
        return values[RANDOM.nextInt(values.length)];
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

    public Texture getTexture() {
        return texture;
    }

    public Item toItem() {
        return new ForagingMineral(this);
    }

    public static ForagingMineralInfo getMineralByNumber(int num) {
        switch (num) {
            case 66: return ForagingMineralInfo.QUARTZ;
            case 67: return ForagingMineralInfo.EARTH_CRISTAL;
            case 68: return ForagingMineralInfo.FROZEN_TEAR;
            case 69: return ForagingMineralInfo.FIRE_QUARTZ;
            case 70: return ForagingMineralInfo.EMERALD;
            case 71: return ForagingMineralInfo.AQUAMARINE;
            case 72: return ForagingMineralInfo.RUBY;
            case 73: return ForagingMineralInfo.AMETHYST;
            case 74: return ForagingMineralInfo.TOPAZ;
            case 75: return ForagingMineralInfo.JADE;
            case 76: return ForagingMineralInfo.DIAMOND;
            case 77: return ForagingMineralInfo.PRISMATIC_SHARD;
            case 78: return ForagingMineralInfo.COPPER;
            case 79: return ForagingMineralInfo.IRON;
            case 80: return ForagingMineralInfo.GOLD;
            case 81: return ForagingMineralInfo.IRIDIUM;
            case 82: return ForagingMineralInfo.COAL;
            default: return null;
        }
    }
}
