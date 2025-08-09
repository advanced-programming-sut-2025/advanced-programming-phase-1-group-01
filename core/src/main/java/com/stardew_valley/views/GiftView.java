package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.User;

public class GiftView extends GameWindow {

    private User friend;

    public GiftView(Stage stage) {
        super("Gift", AssetManager.getAssetManager().getSkin(), "Letter", stage);
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Override
    public void update() {

    }
}
