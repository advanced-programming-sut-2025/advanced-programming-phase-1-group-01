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
    private TextButton deletePlayerButton;
    private TextButton exitButton;

    private final SettingsController controller;

    public SettingsView(SettingsController controller, Stage stage) {
        super("Settings", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        this.controller = controller;
        table = new Table(getSkin());
        nextTurnButton = new TextButton("Next Turn", getSkin());
        deletePlayerButton = new TextButton("Delete Player", getSkin());
        exitButton = new TextButton("Exit", getSkin());

        nextTurnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SettingsView.this.controller.nextTurn();
            }
        });


        deletePlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SettingsView.this.controller.nextTurn();
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SettingsView.this.controller.exitGame();
            }
        });

        table.add(nextTurnButton);
        table.row();
        table.add(deletePlayerButton);
        table.row();
        table.add(exitButton);
        table.setFillParent(true);
        table.center();
        addActor(table);
    }

    @Override
    public void update() {

    }
}
