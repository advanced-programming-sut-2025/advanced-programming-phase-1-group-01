package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.MainMenuController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.User;

import java.util.HashMap;
import java.util.Map;

public class MainMenuView extends View {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Table userInfo;

    private TextButton preGameButton;
    private TextButton profileButton;
    private TextButton logoutButton;
    private TextButton exitButton;
    private Label messageLabel;
    private Texture avatarTexture;
    private Image avatarImage;
    private Label nicknameLabel;
    private Label scoreLabel;

    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getAssetManager().getSkin();

        preGameButton = new TextButton("Pre Game", skin);
        profileButton = new TextButton("Profile", skin);
        logoutButton = new TextButton("Logout", skin);
        exitButton = new TextButton("Exit", skin);
        messageLabel = new Label("", skin);
        Map<String,User> users = controller.getRepo().getUsers();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String,User> entry : users.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(", ");
        }
        messageLabel.setText(stringBuilder.toString());

        User user = controller.getRepo().getCurrentUser();
        String imagePath = user.getAvatarPath();

        this.avatarTexture = new Texture(Gdx.files.internal(imagePath));
        this.avatarImage = new Image(avatarTexture);
        this.nicknameLabel = new Label("Nickname : " + user.getNickname(), skin);
        this.scoreLabel = new Label("Score : " + user.getPlayer().getNumOfCoins(), skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center();
        table.add(preGameButton).width(250).pad(10).row();
        table.add(profileButton).width(250).pad(10).row();
        table.add(logoutButton).width(250).pad(10).row();
        table.add(exitButton).width(250).pad(10).row();
        table.add(messageLabel).pad(10).row();

        stage.addActor(table);

        userInfo = new Table(skin);
        userInfo.setFillParent(true);
        userInfo.top().left();

        userInfo.pad(100);
        userInfo.add(avatarImage).pad(10);
        userInfo.row();
        userInfo.add(nicknameLabel).pad(10);
        userInfo.row();
        userInfo.add(scoreLabel).pad(10);
        stage.addActor(userInfo);

        handleInput();
    }

    @Override
    public void handleInput() {
        preGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.pregame(messageLabel);
            }
        });

        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.profileMenu(messageLabel);
            }
        });

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.logout(messageLabel);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.exit(messageLabel);
            }
        });
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
