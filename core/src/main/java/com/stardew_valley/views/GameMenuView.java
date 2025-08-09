package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.models.AssetManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameMenuView extends View {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Table buttonsTable;

    private SelectBox<String> numOfPlayers;
    private List<TextField> players;
    private List<Label> playersLabel;
    private List<SelectBox<String>> playersMap;
    private TextButton startGame;
    private TextButton nextTurn;
    private TextButton backButton;
    private Label messageLabel;

    private final GameMenuController controller;

    public GameMenuView(GameMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getAssetManager().getSkin();

        numOfPlayers = new SelectBox<>(skin);
        numOfPlayers.setItems("2", "3", "4");
        players = new ArrayList<>();
        playersLabel = new ArrayList<>();
        playersMap = new ArrayList<>();
        startGame = new TextButton("Start Game", skin);
        nextTurn = new TextButton("Next Turn", skin);
        backButton = new TextButton("Back", skin);
        messageLabel = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.center();

        buttonsTable = new Table(skin);
        buttonsTable.center();
        buttonsTable.add(startGame).pad(10).row();
        buttonsTable.add(nextTurn).pad(10).row();
        buttonsTable.add(backButton).pad(10).row();
        buttonsTable.add(messageLabel).pad(10).row();

        Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.add(table).pad(20).row();
        mainTable.add(buttonsTable);
        stage.addActor(mainTable);
        handleInput();
    }

    @Override
    public void handleInput() {

        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    controller.startGame(messageLabel, players);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        nextTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.nextTurn(messageLabel);
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back(messageLabel);
            }
        });

        numOfPlayers.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updatePlayerFields(Integer.parseInt(numOfPlayers.getSelected()) - 1);
            }
        });

        updatePlayerFields(1);
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    private void updatePlayerFields(int count) {
        table.clear();

        table.add("num of players:").pad(10);
        table.add(numOfPlayers).pad(10).row();

        players.clear();
        playersLabel.clear();
        playersMap.clear();

        for (int i = 1; i <= count; i++) {
            Label label = new Label("Player " + i + " Username:", skin);
            TextField textField = new TextField("", skin);
            SelectBox<String> selectBox = new SelectBox<>(skin);
            selectBox.setItems("Map1", "Map2", "Map3");
            players.add(textField);
            playersLabel.add(label);
            playersMap.add(selectBox);

            table.row();
            table.add(label).pad(5);
            table.add(textField).width(200).pad(5);
            table.add(selectBox).width(200).pad(5);
        }
    }



}


