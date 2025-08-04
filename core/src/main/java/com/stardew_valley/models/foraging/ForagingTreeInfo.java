package com.stardew_valley.models.foraging;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.building.TileObject;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.enums.Emoji;
import com.stardew_valley.models.farming.FarmingEnum;

import java.util.Random;

public enum ForagingTreeInfo implements TileObject, FarmingEnum {
    ACORNS("Acorns", Season.SPECIAL, AssetManager.getAssetManager().getAcorn()),
    MAPLE_SEEDS("Maple Seeds", Season.SPECIAL, AssetManager.getAssetManager().getMapleSeed()),
    PINE_CONES("Pine Cones", Season.SPECIAL, AssetManager.getAssetManager().getPineCone()),
    MAHOGANY_SEEDS("Mahogany Seeds", Season.SPECIAL, AssetManager.getAssetManager().getMahoganySeed()),
    MUSHROOMS_TREE_SEEDS("Mushroom Tree Seeds", Season.SPECIAL, AssetManager.getAssetManager().getMushroomTreeSeed()),;

    private final String name;
    private final Season season;
    private final Texture texture;

    ForagingTreeInfo(String name, Season season, Texture texture) {
        this.name = name;
        this.season = season;
        this.texture = texture;
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

    public Texture getTexture() {
        return AssetManager.getAssetManager().defaultTexture();
    }
}
