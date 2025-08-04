package com.stardew_valley.models.farming;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;

public enum SaplingInfo implements FarmingEnum{
    APRICOT_SAPLING("Apricot Sapling", AssetManager.getAssetManager().getApricotSapling()),
    CHERRY_SAPLING("Cherry Sapling", AssetManager.getAssetManager().getCherrySapling()),
    BANANA_SAPLING("Banana Sapling", AssetManager.getAssetManager().getBananaSapling()),
    MANGO_SAPLING("Mango Sapling", AssetManager.getAssetManager().getMangoSapling()),
    ORANGE_SAPLING("Orange Sapling", AssetManager.getAssetManager().getOrangeSapling()),
    PEACH_SAPLING("Peach Sapling", AssetManager.getAssetManager().getPeachSapling()),
    APPLE_SAPLING("Apple Sapling", AssetManager.getAssetManager().getAppleSapling()),
    POMEGRANATE_SAPLING("Pomegranate Sapling", AssetManager.getAssetManager().getPomegranateSapling()),
    ;

    private final String name;
    private final Texture texture;

    SaplingInfo(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    @Override
    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return name;
    }

    public Item toItem() {
        return new Sapling(this);
    }
}
