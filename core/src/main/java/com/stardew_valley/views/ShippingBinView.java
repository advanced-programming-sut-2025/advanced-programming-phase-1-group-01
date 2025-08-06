package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;

public class ShippingBinView extends GameWindow {

    public ShippingBinView() {
        super("Shipping Bin", AssetManager.getAssetManager().getSkin(), "Letter");
    }

    @Override
    public void update() {

    }
}
