package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;

public class GiftView extends GameWindow {

    private Player friend;

    public GiftView(Stage stage) {
        super("Gift", AssetManager.getAssetManager().getSkin(), "Letter", stage);
    }

    public void setFriend(Player friend) {
        this.friend = friend;
    }

    @Override
    public void update() {

    }
}
