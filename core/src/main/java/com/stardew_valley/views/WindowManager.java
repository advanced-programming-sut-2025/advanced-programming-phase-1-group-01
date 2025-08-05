package com.stardew_valley.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;

import java.util.HashMap;
import java.util.Map;

public class WindowManager extends Group {
    private final Map<String, GameWindow> windows = new HashMap<>();
    private Window currentWindow = null;

    private final ButtonGroup<TextButton> buttonGroup;
    private final Table buttonBar;

    public WindowManager(Stage stage) {
        super();
        stage.addActor(this);
        setVisible(false);

        Skin skin = AssetManager.getAssetManager().getSkin();

        buttonGroup = new ButtonGroup<>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
        buttonGroup.setUncheckLast(true);

        TextButton inventoryButton = new TextButton("Inventory", skin);
        TextButton skillsButton = new TextButton("Skills", skin);
        TextButton socialButton = new TextButton("Social", skin);
        TextButton mapButton = new TextButton("Map", skin);
        TextButton settingsButton = new TextButton("Settings", skin);

        buttonGroup.add(inventoryButton, socialButton, skillsButton, mapButton, settingsButton);

        inventoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showWindow("Inventory");
            }
        });
        skillsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showWindow("Skills");
            }
        });
        socialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showWindow("Social");
            }
        });
        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showWindow("Map");
            }
        });
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showWindow("Settings");
            }
        });


        buttonBar = new Table(skin);
        buttonBar.setPosition(stage.getWidth() / 2 + 220, stage.getHeight() / 2 + 325);

        inventoryButton.getLabel().setFontScale(0.8f);
        skillsButton.getLabel().setFontScale(0.8f);
        socialButton.getLabel().setFontScale(0.8f);
        mapButton.getLabel().setFontScale(0.8f);
        settingsButton.getLabel().setFontScale(0.8f);

        buttonBar.add(inventoryButton).size(100, 60).pad(5);
        buttonBar.add(skillsButton).size(100, 60).pad(5);
        buttonBar.add(socialButton).size(100, 60).pad(5);
        buttonBar.add(mapButton).size(100, 60).pad(5);
        buttonBar.add(settingsButton).size(100, 60).pad(5);

        buttonBar.setFillParent(true);
        buttonBar.center();

        this.addActor(buttonBar);
    }

    public void addWindow(String name, GameWindow window) {
        windows.put(name, window);
        window.setVisible(false);
        this.addActor(window);
    }

    public void showWindow(Window windowToShow) {
        if (currentWindow != null) {
            currentWindow.setVisible(false);
        }
        windowToShow.setVisible(true);
        currentWindow = windowToShow;
    }

    public void showWindow(String name) {
        Window windowToShow = windows.get(name);
        if (windowToShow == null) return;

        if (currentWindow != null) {
            currentWindow.setVisible(false);
        }
        windowToShow.setVisible(true);
        currentWindow = windowToShow;
    }

    public void hideCurrent() {
        if (currentWindow != null) {
            currentWindow.setVisible(false);
            currentWindow = null;
        }
    }

    public void update() {
        for (GameWindow window : windows.values()) {
            window.update();
        }
    }
}
