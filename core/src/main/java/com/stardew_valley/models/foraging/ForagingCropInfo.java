package com.stardew_valley.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.farming.FarmingEnum;

import java.util.Random;

public enum ForagingCropInfo implements FarmingEnum {
    COMMON_MUSHROOM("Common Mushroom", Season.SPECIAL, 40, 38, AssetManager.getAssetManager().getCommonMushroom()),
    DAFFODIL("Daffodil", Season.SPRING, 30, 0, AssetManager.getAssetManager().getDaffodil()),
    DANDELION("Dandelion", Season.SPRING, 40, 25, AssetManager.getAssetManager().getDandelion()),
    LEEK("Leek", Season.SPRING, 60, 40, AssetManager.getAssetManager().getLeek()),
    MOREL("Morel", Season.SPRING, 150, 20, AssetManager.getAssetManager().getMorel()),
    SALMONBERRY("Salmonberry", Season.SPRING, 5, 25, AssetManager.getAssetManager().getSalmonberry()),
    SPRING_ONION("Spring Onion", Season.SPRING, 8, 13, AssetManager.getAssetManager().getSpringOnion()),
    WILD_HORSERADISH("Wild Horseradish", Season.SPRING, 50, 13, AssetManager.getAssetManager().getWildHorseradish()),
    FIDDLEHEAD_FERN("Fiddlehead Fern", Season.SUMMER, 90, 25, AssetManager.getAssetManager().getFiddleheadFern()),
    GRAPE("Grape", Season.SUMMER, 80, 38, AssetManager.getAssetManager().getGrape()),
    RED_MUSHROOM("Red Mushroom", Season.SUMMER, 75, -50, AssetManager.getAssetManager().getRedMushroom()),
    SPICE_BERRY("Spice Berry", Season.SUMMER, 80, 25, AssetManager.getAssetManager().getSpiceBerry()),
    SWEET_PEA("Sweet Pea", Season.SUMMER, 50, 0, AssetManager.getAssetManager().getSweetPea()),
    BLACKBERRY("Blackberry", Season.FALL, 25, 25, AssetManager.getAssetManager().getBlackberry()),
    CHANTERELLE("Chanterelle", Season.FALL, 160, 75, AssetManager.getAssetManager().getChanterelle()),
    HAZELNUT("Hazelnut", Season.FALL, 40, 38, AssetManager.getAssetManager().getHazelnut()),
    PURPLE_MUSHROOM("Purple Mushroom", Season.FALL, 90, 30, AssetManager.getAssetManager().getPurpleMushroom()),
    WILD_PLUM("Wild Plum", Season.FALL, 80, 25, AssetManager.getAssetManager().getWildPlum()),
    CROCUS("Crocus", Season.WINTER, 60, 0, AssetManager.getAssetManager().getCrocus()),
    CRYSTAL_FRUIT("Crystal Fruit", Season.WINTER, 150, 63, AssetManager.getAssetManager().getCrystalFruit()),
    HOLLY("Holly", Season.WINTER, 80, -37, AssetManager.getAssetManager().getHolly()),
    SNOW_YAM("Snow Yam", Season.WINTER, 100, 30, AssetManager.getAssetManager().getSnowYam()),
    WINTER_ROOT("Winter Root", Season.WINTER, 70, 25, AssetManager.getAssetManager().getWinterRoot());

    private final String name;
    private final Season season;
    private final int baseSellPrice;
    private final double energy;
    private final Texture texture;

    ForagingCropInfo(String name, Season season, int baseSellPrice, double energy, Texture texture) {
        this.name = name;
        this.season = season;
        this.baseSellPrice = baseSellPrice;
        this.energy = energy;
        this.texture = texture;
    }

    private static final Random RANDOM = new Random();

    public static ForagingCropInfo randomForagingCrop() {
        ForagingCropInfo[] values = ForagingCropInfo.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public double getEnergy() {
        return energy;
    }

    public static ForagingCropInfo fromString(String name) {
        for (ForagingCropInfo foragingCropInfo : values()) {
            if (foragingCropInfo.getName().equalsIgnoreCase(name)) {
                return foragingCropInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return """
                Name: %s
                Season: %s
                BaseSellPrice: %s
                Energy: %s""".formatted(name, season, baseSellPrice, energy);
    }

    public Texture getTexture() {
        return texture;
    }

    public ForagingCrop toItem() {
        return new ForagingCrop(this);
    }

    public static ForagingCropInfo getForagingCropByNumber(int num) {
        switch (num) {
            case 43: return ForagingCropInfo.COMMON_MUSHROOM;
            case 44: return ForagingCropInfo.DAFFODIL;
            case 45: return ForagingCropInfo.DANDELION;
            case 46: return ForagingCropInfo.LEEK;
            case 47: return ForagingCropInfo.MOREL;
            case 48: return ForagingCropInfo.SALMONBERRY;
            case 49: return ForagingCropInfo.SPRING_ONION;
            case 50: return ForagingCropInfo.WILD_HORSERADISH;
            case 51: return ForagingCropInfo.FIDDLEHEAD_FERN;
            case 52: return ForagingCropInfo.GRAPE;
            case 53: return ForagingCropInfo.RED_MUSHROOM;
            case 54: return ForagingCropInfo.SPICE_BERRY;
            case 55: return ForagingCropInfo.SWEET_PEA;
            case 56: return ForagingCropInfo.BLACKBERRY;
            case 57: return ForagingCropInfo.CHANTERELLE;
            case 58: return ForagingCropInfo.HAZELNUT;
            case 59: return ForagingCropInfo.PURPLE_MUSHROOM;
            case 60: return ForagingCropInfo.WILD_PLUM;
            case 61: return ForagingCropInfo.CROCUS;
            case 62: return ForagingCropInfo.CRYSTAL_FRUIT;
            case 63: return ForagingCropInfo.HOLLY;
            case 64: return ForagingCropInfo.SNOW_YAM;
            case 65: return ForagingCropInfo.WINTER_ROOT;
            default: return null;
        }
    }
}
