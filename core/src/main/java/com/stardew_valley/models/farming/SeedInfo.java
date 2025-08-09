package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.dateTime.Season;

import java.util.Random;

public enum SeedInfo implements FarmingEnum {
    JAZZ_SEEDS("Jazz Seeds", Season.SPRING, AssetManager.getAssetManager().getJazzSeeds()),
    CARROT_SEEDS("Carrot Seeds", Season.SPRING, AssetManager.getAssetManager().getCarrotSeeds()),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", Season.SPRING, AssetManager.getAssetManager().getCauliflowerSeeds()),
    COFFEE_BEAN("Coffee Bean", Season.SPRING, AssetManager.getAssetManager().getCoffeeBean()),
    GARLIC_SEEDS("Garlic Seeds", Season.SPRING, AssetManager.getAssetManager().getGarlicSeeds()),
    BEAN_STARTER("Bean Starter", Season.SPRING, AssetManager.getAssetManager().getBeanStarter()),
    KALE_SEEDS("Kale Seeds", Season.SPRING, AssetManager.getAssetManager().getKaleSeeds()),
    PARSNIP_SEEDS("Parsnip Seeds", Season.SPRING, AssetManager.getAssetManager().getParsnipSeeds()),
    POTATO_SEEDS("Potato Seeds", Season.SPRING, AssetManager.getAssetManager().getPotatoSeeds()),
    RHUBARB_SEEDS("Rhubarb Seeds", Season.SPRING, AssetManager.getAssetManager().getRhubarbSeeds()),
    STRAWBERRY_SEEDS("Strawberry Seeds", Season.SPRING, AssetManager.getAssetManager().getStrawberrySeeds()),
    TULIP_BULB("Tulip Bulb", Season.SPRING, AssetManager.getAssetManager().getTulipBulb()),
    RICE_SHOOT("Rice Shoot", Season.SPRING, AssetManager.getAssetManager().getRiceShoot()),
    BLUEBERRY_SEEDS("Blueberry Seeds", Season.SUMMER, AssetManager.getAssetManager().getBlueberrySeeds()),
    CORN_SEEDS("Corn Seeds", Season.SUMMER, AssetManager.getAssetManager().getCornSeeds()),
    HOPS_STARTER("Hops Starter", Season.SUMMER, AssetManager.getAssetManager().getHopsStarter()),
    PEPPER_SEEDS("Pepper Seeds", Season.SUMMER, AssetManager.getAssetManager().getPepperSeeds()),
    MELON_SEEDS("Melon Seeds", Season.SUMMER, AssetManager.getAssetManager().getMelonSeeds()),
    POPPY_SEEDS("Poppy Seeds", Season.SUMMER, AssetManager.getAssetManager().getPoppySeeds()),
    RADISH_SEEDS("Radish Seeds", Season.SUMMER, AssetManager.getAssetManager().getRadishSeeds()),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", Season.SUMMER, AssetManager.getAssetManager().getRedCabbageSeeds()),
    STARFRUIT_SEEDS("Starfruit Seeds", Season.SUMMER, AssetManager.getAssetManager().getStarfruitSeeds()),
    SPANGLE_SEEDS("Spangle Seeds", Season.SUMMER, AssetManager.getAssetManager().getSpangleSeeds()),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", Season.SUMMER, AssetManager.getAssetManager().getSummerSquashSeeds()),
    SUNFLOWER_SEEDS("Sunflower Seeds", Season.SUMMER, AssetManager.getAssetManager().getSunflowerSeeds()),
    TOMATO_SEEDS("Tomato Seeds", Season.SUMMER, AssetManager.getAssetManager().getTomatoSeeds()),
    WHEAT_SEEDS("Wheat Seeds", Season.SUMMER, AssetManager.getAssetManager().getWheatSeeds()),
    AMARANTH_SEEDS("Amaranth Seeds", Season.FALL, AssetManager.getAssetManager().getAmaranthSeeds()),
    ARTICHOKE_SEEDS("Artichoke Seeds", Season.FALL, AssetManager.getAssetManager().getArtichokeSeeds()),
    BEET_SEEDS("Beet Seeds", Season.FALL, AssetManager.getAssetManager().getBeetSeeds()),
    BOKCHOY_SEEDS("Bokchoy Seeds", Season.FALL, AssetManager.getAssetManager().getBokChoySeeds()),
    BROCCOLI_SEEDS("Broccoli Seeds", Season.FALL, AssetManager.getAssetManager().getBroccoliSeeds()),
    CRANBERRY_SEEDS("Cranberry Seeds", Season.FALL, AssetManager.getAssetManager().getCranberrySeeds()),
    EGGPLANT_SEEDS("Eggplant Seeds", Season.FALL, AssetManager.getAssetManager().getEggplantSeeds()),
    FAIRY_SEEDS("Fairy Seeds", Season.FALL, AssetManager.getAssetManager().getFairySeeds()),
    GRAPE_STARTER("Grape Starter", Season.FALL, AssetManager.getAssetManager().getGrapeStarter()),
    PUMPKIN_SEEDS("Pumpkin Seeds", Season.FALL, AssetManager.getAssetManager().getPumpkinSeeds()),
    YAM_SEEDS("Yam Seeds", Season.FALL, AssetManager.getAssetManager().getYamSeeds()),
    RARE_SEEDS("Rare Seeds", Season.FALL, AssetManager.getAssetManager().getRareSeeds()),
    POWDERMELON_SEEDS("Powdermelon Seeds", Season.WINTER, AssetManager.getAssetManager().getPowdermelonSeeds()),
    ANCIENT_SEEDS("Ancient Seeds", Season.SPECIAL, AssetManager.getAssetManager().getAncientSeeds()),
    MIXED_SEEDS("Mixed Seeds", Season.SPECIAL, AssetManager.getAssetManager().getMixedSeeds());

    private final String name;
    private final Season season;
    private final Texture texture;

    SeedInfo(String name, Season season, Texture texture) {
        this.name = name;
        this.season = season;
        this.texture = texture;

    }

    private static final Random RANDOM = new Random();

    public static SeedInfo randomForagingSeed() {
        SeedInfo[] seedInfos = SeedInfo.values();
        return seedInfos[RANDOM.nextInt(seedInfos.length)];
    }

    public Season getSeason() {
        return season;
    }

    public String getName() {
        return name;
    }

    public static SeedInfo fromString(String name) {
        for (SeedInfo seedInfo : SeedInfo.values()) {
            if (seedInfo.getName().equalsIgnoreCase(name)) {
                return seedInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }


    public Seed toItem() {
        return new Seed(this);
    }

    public String getSymbol() {
        return null;
    }

    public Texture getTexture() {
        return texture;
    }

    public static SeedInfo getSeedByNumber(int num) {
        switch (num) {
            case 1: return SeedInfo.JAZZ_SEEDS;
            case 2: return SeedInfo.CARROT_SEEDS;
            case 3: return SeedInfo.CAULIFLOWER_SEEDS;
            case 4: return SeedInfo.COFFEE_BEAN;
            case 5: return SeedInfo.GARLIC_SEEDS;
            case 6: return SeedInfo.BEAN_STARTER;
            case 7: return SeedInfo.KALE_SEEDS;
            case 8: return SeedInfo.PARSNIP_SEEDS;
            case 9: return SeedInfo.POTATO_SEEDS;
            case 10: return SeedInfo.RHUBARB_SEEDS;
            case 11: return SeedInfo.STRAWBERRY_SEEDS;
            case 12: return SeedInfo.TULIP_BULB;
            case 13: return SeedInfo.RICE_SHOOT;
            case 14: return SeedInfo.BLUEBERRY_SEEDS;
            case 15: return SeedInfo.CORN_SEEDS;
            case 16: return SeedInfo.HOPS_STARTER;
            case 17: return SeedInfo.PEPPER_SEEDS;
            case 18: return SeedInfo.MELON_SEEDS;
            case 19: return SeedInfo.POPPY_SEEDS;
            case 20: return SeedInfo.RADISH_SEEDS;
            case 21: return SeedInfo.RED_CABBAGE_SEEDS;
            case 22: return SeedInfo.STARFRUIT_SEEDS;
            case 23: return SeedInfo.SPANGLE_SEEDS;
            case 24: return SeedInfo.SUMMER_SQUASH_SEEDS;
            case 25: return SeedInfo.SUNFLOWER_SEEDS;
            case 26: return SeedInfo.TOMATO_SEEDS;
            case 27: return SeedInfo.WHEAT_SEEDS;
            case 28: return SeedInfo.AMARANTH_SEEDS;
            case 29: return SeedInfo.ARTICHOKE_SEEDS;
            case 30: return SeedInfo.BEET_SEEDS;
            case 31: return SeedInfo.BOKCHOY_SEEDS;
            case 32: return SeedInfo.BROCCOLI_SEEDS;
            case 33: return SeedInfo.CRANBERRY_SEEDS;
            case 34: return SeedInfo.EGGPLANT_SEEDS;
            case 35: return SeedInfo.FAIRY_SEEDS;
            case 36: return SeedInfo.GRAPE_STARTER;
            case 37: return SeedInfo.PUMPKIN_SEEDS;
            case 38: return SeedInfo.YAM_SEEDS;
            case 39: return SeedInfo.RARE_SEEDS;
            case 40: return SeedInfo.POWDERMELON_SEEDS;
            case 41: return SeedInfo.ANCIENT_SEEDS;
            case 42: return SeedInfo.MIXED_SEEDS;
            default: return null;
        }
    }
}
