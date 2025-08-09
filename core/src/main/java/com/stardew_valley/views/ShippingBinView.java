package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew_valley.models.AssetManager;

public class ShippingBinView extends GameWindow {

    public ShippingBinView(Stage stage) {
        super("Shipping Bin", AssetManager.getAssetManager().getSkin(), "Letter", stage);
    }

    @Override
    public void update() {

    }
}
