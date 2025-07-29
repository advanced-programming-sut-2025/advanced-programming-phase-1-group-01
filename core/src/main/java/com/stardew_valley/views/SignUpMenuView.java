package com.stardew_valley.views;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.SignUpMenuController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;
import java.util.List;
import java.util.ArrayList;

public class SignUpMenuView extends View {
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
    private Label messageLabel;

    private final SignUpMenuController controller;

    public SignUpMenuView(SignUpMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getAssetManager().getSkin();

        username = new TextField("", skin);
        password = new TextField("", skin);
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        confirmPassword = new TextField("", skin);
        confirmPassword.setPasswordMode(true);
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
        messageLabel = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);

        table.add("username: ").pad(10);
        table.add(username).width(250).pad(10).row();
        table.add("password: ").pad(10);
        table.add(password).width(250).pad(10).row();
        table.add("confirmPassword: ").pad(10);
        table.add(confirmPassword).width(250).pad(10).row();
        table.add("nickname: ").pad(10);
        table.add(nickname).width(250).pad(10).row();
        table.add("email: ").pad(10);
        table.add(email).width(250).pad(10).row();
        table.add("gender: ").pad(10);
        table.add(gender).width(250).pad(10).row();
        table.add("securityQuestion: ").pad(10);
        table.add(securityQuestion).width(250).pad(10).row();
        table.add("answer: ").pad(10);
        table.add(answer).width(250).pad(10).row();

        table.add(signupButton).width(230).pad(10);
        table.add(loginButton).width(230).pad(10).row();
        table.add(randomPasswordButton).width(230).pad(10);
        table.add(exitButton).width(230).pad(10).row();

        Table messageTable = new Table(skin);
        messageTable.add(messageLabel).pad(10).center();

        Table rootTable = new Table(skin);
        rootTable.setFillParent(true);
        rootTable.center();
        rootTable.add(table).pad(10).row();
        rootTable.add(messageTable).pad(10).center();

        stage.addActor(rootTable);

        handleInput();
    }


    @Override
    public void handleInput() {

        signupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<String> data = new ArrayList<>();
                data.add(username.getText());
                data.add(password.getText());
                data.add(confirmPassword.getText());
                data.add(nickname.getText());
                data.add(email.getText());
                data.add(gender.getSelected().toString());
                data.add(securityQuestion.getSelected().getQuestion());
                data.add(answer.getText());

                controller.register(data,messageLabel);
            }
        });

        randomPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.randomPassword(messageLabel);
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.login(messageLabel);
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
