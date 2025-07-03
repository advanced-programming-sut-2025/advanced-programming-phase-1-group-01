package com.stardew_valley.models;

public class AssetManager {
    private static AssetManager assetManager;

    public static AssetManager getAssetManager() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
        return assetManager;
    }
}
