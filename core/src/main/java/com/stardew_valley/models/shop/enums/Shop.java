package com.stardew_valley.models.shop.enums;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Position;


public enum Shop {
    BLACKSMITH(new Position(18,187), AssetManager.getAssetManager().getBlackSmithOut()),
    CARPENTER(new Position(18,37), AssetManager.getAssetManager().getCarpenterOut()),
    FISH_SHOP(new Position(183,187), AssetManager.getAssetManager().getFishShopOut()),
    JOJAMART(new Position(201, 37), AssetManager.getAssetManager().getJojamartOut()),
    PIERRE(new Position(56,187), AssetManager.getAssetManager().getPierrOut()),
    RANCH(new Position(56,37), AssetManager.getAssetManager().getRanchOut()),
    SALOON(new Position(161,37), AssetManager.getAssetManager().getSaloonOut()),;

    private final Position bottomLeft;
    private final Texture texture;

    Shop(Position bl, Texture texture) {
        this.bottomLeft = bl;
        this.texture = texture;
    }

    public Position getBottomLeft() { return bottomLeft; }
    public Texture getTexture() { return texture; }
}
