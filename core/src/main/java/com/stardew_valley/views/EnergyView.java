package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;

public class EnergyView {

    private final Player player;
    private final Stage stage;
    private final Image heartImage;
    private final Label energyLabel;
    private final Texture[] heartTextures;

    public EnergyView(Player player) {
        this.player = player;
        this.stage = new Stage(new ScreenViewport());

        heartTextures = new Texture[11];
        for (int i = 0; i <= 10; i++) {
            heartTextures[i] = new Texture("energy/" + i + ".png");
        }
        energyLabel = new Label("", AssetManager.getAssetManager().getSkin());
        energyLabel.setPosition(1570, 1030);
        energyLabel.setColor(Color.RED);

        heartImage = new Image(new TextureRegionDrawable(heartTextures[10]));
        heartImage.setPosition(1670, 1020);
        heartImage.setScale(2f,2f);

        stage.addActor(energyLabel);
        stage.addActor(heartImage);
    }

    public void updateEnergy() {
        int energy = (int) player.getEnergy().getAmount();
        double maxEnergy = player.getEnergy().getMaxEnergy();
        int heartIndex = (int) ((energy / maxEnergy) * 10);
        if (heartIndex < 0) heartIndex = 0;
        if (heartIndex > 10) heartIndex = 10;

        heartImage.setDrawable(new TextureRegionDrawable(heartTextures[heartIndex]));

        energyLabel.setText(player.getEnergy().toString());
        if (player.getEnergy().isUnlimited()) {
            energyLabel.setPosition(1500,1030);
            energyLabel.setText("unlimited!");
        }
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
}

