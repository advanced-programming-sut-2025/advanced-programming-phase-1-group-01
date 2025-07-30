package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.ProfileMenuController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.data.FileManager;
import com.stardew_valley.models.data.User;

import java.util.function.Consumer;

public class ProfileMenuView extends View {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Table userInfoTable;

    private Label userInfoLabel;
    private Texture avatarTexture;
    private Image avatarImage;
    private TextButton changeUsername;
    private TextButton changePassword;
    private TextButton changeEmail;
    private TextButton changeNickname;
    private TextButton changeAvatar;
    private TextButton clearAccount;
    private TextButton back;
    private Label messageLabel;

    private final ProfileMenuController controller;

    public ProfileMenuView(ProfileMenuController controller) {
        this.controller = controller;
        this.skin = AssetManager.getAssetManager().getSkin();

        User user = controller.getRepo().getCurrentUser();
        String avatarPath = user.getAvatarPath();
        userInfoLabel = new Label("nickname: " + user.getNickname() + "\n\n" + "username: " + user.getUsername() +
            "\n\n" + "email: " + user.getEmail() + "\n\n" + "play game: " + user.getNumOfPlayedGames() + "\n\n" + "coins: " +
            user.getNumOfPlayedGames(), skin);
        avatarTexture = new Texture(avatarPath);
        avatarImage = new Image(avatarTexture);

        changeUsername = new TextButton("changeUsername", skin);
        changePassword = new TextButton("changePassword", skin);
        changeEmail = new TextButton("changeEmail", skin);
        changeNickname = new TextButton("changeNickname", skin);
        changeAvatar = new TextButton("changeAvatar", skin);
        clearAccount = new TextButton("clearAccount", skin);
        back = new TextButton("back", skin);
        messageLabel = new Label("", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        table.center();

        table.add(changeUsername).pad(10).row();
        table.add(changePassword).pad(10).row();
        table.add(changeNickname).pad(10).row();
        table.add(changeEmail).pad(10).row();
        table.add(changeAvatar).pad(10).row();
        table.add(clearAccount).pad(10).row();
        table.add(back).pad(10).row();
        table.add(messageLabel).pad(10);
        stage.addActor(table);

        userInfoTable = new Table(skin);
        userInfoTable.setFillParent(true);
        userInfoTable.top().left();
        userInfoTable.pad(100);
        userInfoTable.add(avatarImage).pad(10).row();
        userInfoTable.add(userInfoLabel).pad(10).row();
        stage.addActor(userInfoTable);

        handleInput();
    }

    @Override
    public void handleInput() {
        changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                promptForUsername(newUsername -> {
                    String message = controller.changeUsername(newUsername);
                    messageLabel.setText(message);
                    refreshUserInfo();
                });
            }
        });

        changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                promptForPassword(newPass -> {
                    String resultMessage = controller.changePassword(newPass);
                    messageLabel.setText(resultMessage);
                    refreshUserInfo();
                });
            }
        });


        changeNickname.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                promptForNickname(newNickname -> {
                    String message = controller.changeNickname(newNickname);
                    messageLabel.setText(message);
                    refreshUserInfo();
                });
            }
        });

        changeEmail.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                promptForEmail(newEmail -> {
                    String message = controller.changeEmail(newEmail);
                    messageLabel.setText(message);
                    refreshUserInfo();
                });
            }
        });

        changeAvatar.addListener(new ClickListener() {
             @Override
             public void clicked(InputEvent event, float x, float y) {
                 promptForAvatarSelection(avatarIndex -> {
                     String avatarPath = "avatars/avatar" + avatarIndex + ".png";
                     controller.getRepo().getCurrentUser().setAvatarPath(avatarPath);
                     messageLabel.setText("Avatar changed successfully!");
                     refreshUserInfo();
                 });
             }
         });

        clearAccount.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileManager.clearFile();
                messageLabel.setText("account has been cleared!");
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back(messageLabel);
            }
        });
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public void promptForUsername(Consumer<String> onSubmit) {
        final TextField usernameField = new TextField("", skin);
        usernameField.setMessageText("Enter new username");

        Dialog dialog = new Dialog("Change Username", skin) {
            @Override
            protected void result(Object object) {
                boolean okPressed = Boolean.TRUE.equals(object);
                if (okPressed) {
                    onSubmit.accept(usernameField.getText().trim());
                }
                this.hide();
                Gdx.input.setInputProcessor(stage);
            }
        };

        dialog.getContentTable().add(usernameField).width(300).pad(10);
        dialog.button("OK", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }

    public void promptForNickname(Consumer<String> onSubmit) {
        final TextField nicknameField = new TextField("", skin);
        nicknameField.setMessageText("Enter new nickname");

        Dialog dialog = new Dialog("Change Nickname", skin) {
            @Override
            protected void result(Object object) {
                boolean okPressed = Boolean.TRUE.equals(object);
                if (okPressed) {
                    onSubmit.accept(nicknameField.getText().trim());
                }
                this.hide();
                Gdx.input.setInputProcessor(stage);
            }
        };

        dialog.getContentTable().add(nicknameField).width(300).pad(10);
        dialog.button("OK", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }

    public void promptForEmail(Consumer<String> onSubmit) {
        final TextField emailField = new TextField("", skin);
        emailField.setMessageText("Enter new email");

        Dialog dialog = new Dialog("Change Email", skin) {
            @Override
            protected void result(Object object) {
                boolean okPressed = Boolean.TRUE.equals(object);
                if (okPressed) {
                    onSubmit.accept(emailField.getText().trim());
                }
                this.hide();
                Gdx.input.setInputProcessor(stage);
            }
        };

        dialog.getContentTable().add(emailField).width(300).pad(10);
        dialog.button("OK", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }

    public void promptForPassword(Consumer<String> onSubmit) {
        final TextField passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        passwordField.setMessageText("Enter new password");

        Dialog dialog = new Dialog("Change Password", skin) {
            @Override
            protected void result(Object object) {
                boolean okPressed = Boolean.TRUE.equals(object);
                if (okPressed) {
                    onSubmit.accept(passwordField.getText().trim());
                }
                this.hide();
                Gdx.input.setInputProcessor(stage);
            }
        };

        dialog.getContentTable().add(passwordField).width(300).pad(10);
        dialog.button("OK", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }

    public void promptForAvatarSelection(Consumer<Integer> onAvatarSelected) {
        Dialog avatarDialog = new Dialog("Select Avatar", skin);
        Table content = new Table();

        int avatarsPerRow = 4;

        for (int i = 1; i <= 8; i++) {
            final int avatarIndex = i;
            final Texture texture = new Texture(Gdx.files.internal("avatars/avatar" + i + ".png"));
            final Image avatarImg = new Image(texture);

            avatarImg.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    avatarDialog.hide();
                    onAvatarSelected.accept(avatarIndex);
                    texture.dispose();
                }
            });

            content.add(avatarImg).pad(10);

            if (i % avatarsPerRow == 0) {
                content.row();
            }
        }

        avatarDialog.getContentTable().add(content);
        avatarDialog.button("Cancel", false);
        avatarDialog.show(stage);
    }

    private void refreshUserInfo() {
        User user = controller.getRepo().getCurrentUser();
        userInfoLabel.setText("nickname: " + user.getNickname() + "\n\n" + "username: " + user.getUsername() +
            "\n\n" + "email: " + user.getEmail() + "\n\n" + "play game: " + user.getNumOfPlayedGames() + "\n\n" + "coins: " +
            user.getNumOfPlayedGames());

        Texture newTexture = new Texture(user.getAvatarPath());
        avatarImage.setDrawable(new Image(newTexture).getDrawable());

    }
}
