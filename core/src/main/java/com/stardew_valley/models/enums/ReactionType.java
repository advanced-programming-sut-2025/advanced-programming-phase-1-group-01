package com.stardew_valley.models.enums;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;

public enum ReactionType {
    LAUGH(AssetManager.getAssetManager().getHappyFaceTex()),
    LIKE(AssetManager.getAssetManager().getThumbUpTex()),
    DISLIKE(AssetManager.getAssetManager().getDislikedTex()),
    ANGER(AssetManager.getAssetManager().getSadFaceTex()),
    LOVE(AssetManager.getAssetManager().getSmileyTex()),
    HI(AssetManager.getAssetManager().getWavingHandTex()),
    OK(AssetManager.getAssetManager().getOkHandTex()),
    SURPRISED(AssetManager.getAssetManager().getSurprisedTex()),
    SAD(AssetManager.getAssetManager().getSadTex()),
    CONFUSED(AssetManager.getAssetManager().getConfusedTex()),
    CELEBRATE(AssetManager.getAssetManager().getPartyTex()),
    THINKING(AssetManager.getAssetManager().getThinkingTex()),
    THANKS(AssetManager.getAssetManager().getThanksTex()),
    SLEEPY(AssetManager.getAssetManager().getSleepTex());

    private final Texture texture;

    ReactionType(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public static int toId(ReactionType type) {
        return type.ordinal();
    }

    public static ReactionType fromId(int id) {
        return ReactionType.values()[id];
    }

}
