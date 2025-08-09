package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.controllers.SettingsController;
import com.stardew_valley.models.AssetManager;

public class SettingsView extends GameWindow {
    private Table table;
    private TextButton nextTurnButton;

    private final SettingsController controller;

    public SettingsView(SettingsController controller, Stage stage) {
        super("Settings", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        this.controller = controller;
        table = new Table(getSkin());
        nextTurnButton = new TextButton("Next Turn", getSkin());

        nextTurnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SettingsView.this.controller.nextTurn();
            }
        });

        table.add(nextTurnButton);
        table.setFillParent(true);
        table.center();
        addActor(table);
    }

    @Override
    public void update() {

    }
}
