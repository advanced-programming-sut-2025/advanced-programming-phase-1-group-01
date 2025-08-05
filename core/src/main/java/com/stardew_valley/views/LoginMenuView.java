package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.LoginMenuController;
import com.stardew_valley.controllers.SignUpMenuController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.data.User;

import java.util.List;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class LoginMenuView extends View {
    private Stage stage;
    private Table table;
    private Skin skin;

    private TextField username;
    private TextField password;
    private SelectBox<String> stayLogin;
    private TextButton login;
    private TextButton back;
    private TextButton loadGame;
    private TextButton forgetPassword;
    private Label messageLabel;

    private LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getAssetManager().getSkin();

        username = new TextField("", skin);
        password = new TextField("", skin);
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        stayLogin = new SelectBox<>(skin);
        stayLogin.setItems("Yes","No");
        login = new TextButton("Login", skin);
        back = new TextButton("Back", skin);
        loadGame = new TextButton("Load Game", skin);
        forgetPassword = new TextButton("Forget Password", skin);
        messageLabel = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center();

        table.add("Username").pad(10);
        table.add(username).width(250).pad(10).row();
        table.add("Password").pad(10);
        table.add(password).width(250).pad(10).row();
        table.add("stayLogin").pad(10);
        table.add(stayLogin).width(250).pad(10).row();
        table.add(login).width(250).pad(10);
        table.add(loadGame).width(250).pad(10).row();
        table.add(forgetPassword).width(250).pad(10);
        table.add(back).width(250).pad(10).row();
        table.add(messageLabel).colspan(2).pad(10);
        stage.addActor(table);

        handleInput();
    }

    @Override
    public void handleInput() {
        login.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<String> data = new ArrayList();
                data.add(username.getText());
                data.add(password.getText());
                data.add(stayLogin.getSelected());
                controller.login(data,messageLabel);
            }
        });

        forgetPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleForgetPassword();
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back(messageLabel);
            }
        });

        loadGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.loadGame(messageLabel);
            }
        });
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public void promptForForgetPassword(String securityQuestion, Consumer<String> onSubmit) {
        final Label questionLabel = new Label(securityQuestion, skin);
        final TextField answerField = new TextField("", skin);
        answerField.setMessageText("Answer to security question");

        Dialog dialog = new Dialog("Forgot Password", skin) {
            @Override
            protected void result(Object object) {
                boolean okPressed = Boolean.TRUE.equals(object);
                if (okPressed) {
                    String answer = answerField.getText().trim();
                    onSubmit.accept(answer);
                }
                this.hide();
                Gdx.input.setInputProcessor(stage);
            }
        };

        dialog.getContentTable().pad(10);
        dialog.getContentTable().add(questionLabel).left().padBottom(5).row();
        dialog.getContentTable().add(answerField).width(300).padBottom(10).row();

        dialog.button("OK", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }

    public void handleForgetPassword() {
        if (username.getText().isEmpty()) {
            messageLabel.setText("Please enter your username");
            return;
        }

        if (controller.getRepo().getUserByUsername(username.getText()) == null) {
            messageLabel.setText("Username not found");
            return;
        }
        User user = controller.getRepo().getUserByUsername(username.getText());
        String securityQuestion = user.getSecurityQuestion().getQuestion();

        promptForForgetPassword(securityQuestion, answer -> {
            if (!user.getSecurityAnswer().equals(answer)) {
                    messageLabel.setText("Wrong answer");
            }
            else {
                messageLabel.setText("Your password is: " + user.getPassword());
            }
        });
    }
}
