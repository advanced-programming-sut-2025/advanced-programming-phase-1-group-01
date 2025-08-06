package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.models.AssetManager;

public class BlackSmithView {
    private Stage stage;
    private Image blacksmithIn;
    private Image blacksmithOut;

    public BlackSmithView() {
        blacksmithIn = new Image(AssetManager.getAssetManager().getBlackSmithIn());
        blacksmithOut = new Image(AssetManager.getAssetManager().getBlackSmithOut());
    }

    public void updateBlacksmith() {
        stage = new Stage();
        stage.addActor(blacksmithIn);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
}
