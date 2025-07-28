package com.stardew_valley.views;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileObject;

import java.util.List;

public class ProcessingMachine extends Group {
    private final ProgressBar progressBar;
    private final TextButton collectButton;
    private float progress = 0;
    private boolean hasProduct = false;

    public ProcessingMachine(TextureRegion region) {
        Image machineImage = new Image(region);
        addActor(machineImage);

        Skin skin = AssetManager.getAssetManager().getSkin();
        progressBar = new ProgressBar(0f, 1f, 0.01f, false, skin);
        progressBar.setValue(progress);
        progressBar.setWidth(100);
        progressBar.setHeight(15);
        progressBar.setPosition(10, machineImage.getHeight() + 10);

        collectButton = new TextButton("Collect", skin);
        collectButton.setPosition(10, -40);
        collectButton.setVisible(false);

        collectButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                resetProgress();
                return true;
            }
        });

        addActor(progressBar);

        setSize(machineImage.getWidth(), machineImage.getHeight());
    }

    public void update(float delta) {
        if (hasProduct) {
            if (progress < 1) {
                progress += delta * 0.1f;
                progressBar.setValue(progress);
                if (progress >= 1) {
                    collectButton.setVisible(true);
                }
            }
        }
    }

    public void setHasProduct(boolean hasProduct) {
        this.hasProduct = hasProduct;
    }

    public boolean getHasProduct() {
        return hasProduct;
    }


    private void resetProgress() {
        progress = 0;
        progressBar.setValue(progress);
        collectButton.setVisible(false);
    }

    public void setTiles(List<List<Tile>> tiles, int xTile, int yTile, TileObject tileObject) {
        for (int i = xTile; i < xTile + 3; i++) {
            for (int j = yTile; j < yTile + 3; j++) {
                tiles.get(i).get(j).setMovable(false);
                tiles.get(i).get(j).setObject(tileObject);
            }
        }
    }
}
