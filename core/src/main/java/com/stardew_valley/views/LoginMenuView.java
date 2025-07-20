package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.LoginMenuController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;

public class LoginMenuView extends View {
    private Stage stage;
    private Table table;
    private Skin skin;

    private TextField username;
    private TextField password;
    private TextField confirmPassword;
    private TextField nickname;
    private TextField email;
    private SelectBox<Gender> gender;
    private SelectBox<SecurityQuestion> securityQuestion;
    private TextField answer;
    private TextButton signupButton;
    private TextButton loginButton;
    private TextButton exitButton;
    private TextButton randomPasswordButton;
    private Label titleLabel;
    private Label randomPasswordLabel;
    private Label messageLabel;

    private final LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getSkin();

        username = new TextField("", skin);
        password = new TextField("", skin);
        password.setPasswordCharacter('*');
        confirmPassword = new TextField("", skin);
        confirmPassword.setPasswordCharacter('*');
        nickname = new TextField("", skin);
        email = new TextField("", skin);
        gender = new SelectBox<>(skin);
        gender.setItems(Gender.values());
        securityQuestion = new SelectBox<>(skin);
        securityQuestion.setItems(SecurityQuestion.values());
        answer = new TextField("", skin);
        signupButton = new TextButton("Sign Up", skin);
        loginButton = new TextButton("Login", skin);
        exitButton = new TextButton("Exit", skin);
        randomPasswordButton = new TextButton("Random Password", skin);
        titleLabel = new Label("Sign Up Menu", skin, "title");
        randomPasswordLabel = new Label("", skin);
        messageLabel = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center();

        table.add(titleLabel).colspan(2).padBottom(50).row();

        table.add("username: ").pad(10);
        table.add(username).width(250).pad(15).row();
        table.add("password: ").pad(10);
        table.add(password).width(250).pad(15);
        table.add(randomPasswordLabel).pad(15).row();
        table.add("confirmPassword: ").pad(10);
        table.add(confirmPassword).width(250).pad(15).row();
        table.add("nickname: ").pad(10);
        table.add(nickname).width(250).pad(15).row();
        table.add("email: ").pad(10);
        table.add(email).width(250).pad(15).row();
        table.add("gender: ").pad(10);
        table.add(gender).width(250).pad(15).row();
        table.add("securityQuestion: ").pad(10);
        table.add(securityQuestion).width(250).pad(15).row();
        table.add("answer: ").pad(10);
        table.add(answer).width(250).pad(15).row();

        table.add(signupButton).width(230).pad(15);
        table.add(loginButton).width(230).pad(15).row();
        table.add(randomPasswordButton).width(230).pad(15);
        table.add(exitButton).width(230).pad(15).row();
        table.add(messageLabel).colspan(2).pad(15);

        stage.addActor(table);

        handleInput();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
