package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;

public class GiftView extends GameWindow {

    private Player friend;

    public GiftView() {
        super("Gift", AssetManager.getAssetManager().getSkin(), "Letter");
    }

    public void setFriend(Player friend) {
        this.friend = friend;
    }

    @Override
    public void update() {

    }
}
