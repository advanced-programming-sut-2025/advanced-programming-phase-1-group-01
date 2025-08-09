package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.controllers.LobbyController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.LobbyData;
import com.stardew_valley.models.Result;

import java.util.List;

public class LobbyView extends ScreenAdapter implements InputProcessor {
    private final Stage stage;
    private final LobbyController controller;

    private Table lobbyTable;


    public LobbyView(LobbyController controller) {
        this.controller = controller;
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(new InputMultiplexer(stage));

        createUI();
        refreshLobbyList(controller.getLobbies());
    }

    private void createUI() {
        Skin skin = AssetManager.getAssetManager().getSkin();

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        TextButton createLobbyBtn = new TextButton("Create Lobby", skin);
        createLobbyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showCreateLobbyDialog(skin);
            }
        });

        TextButton recentBtn = new TextButton("Recent Lobbies", skin);
        recentBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                List<LobbyData> recent = controller.loadRecentLobbies();
                refreshLobbyList(recent);
            }
        });

        lobbyTable = new Table(skin);
        ScrollPane scrollPane = new ScrollPane(lobbyTable, skin);

        root.top().pad(10);
        root.add(createLobbyBtn).pad(5);
        root.add(recentBtn).pad(5);
        root.row();
        root.add(scrollPane).colspan(2).expand().fill();
    }

    private void refreshLobbyList(List<LobbyData> lobbyList) {
        lobbyTable.clear();

        if (lobbyList == null) {
            return;
        }

        for (LobbyData lobby : lobbyList) {
            lobbyTable.add(lobby.getName()).pad(5);
            lobbyTable.add(lobby.getPlayers().size() + " players").pad(5);

            TextButton joinBtn = new TextButton("Join", AssetManager.getAssetManager().getSkin());
            joinBtn.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    if (lobby.isPrivate()) {
                        showPasswordDialog(lobby);
                    } else {
                        Result result = controller.joinLobby(lobby.getId(), null);
                        showMessage(result.message());
                        if (result.success()) {
                            refreshLobbyList(controller.getLobbies());
                        }
                    }
                }
            });
            lobbyTable.add(joinBtn).pad(5);

            lobbyTable.row();
        }
    }


    private void showCreateLobbyDialog(Skin skin) {
        TextField nameField = new TextField("", skin);
        CheckBox privateBox = new CheckBox("Private", skin);
        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setDisabled(true);

        CheckBox visibleBox = new CheckBox("Visible", skin);
        visibleBox.setChecked(true);

        privateBox.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                passwordField.setDisabled(!privateBox.isChecked());
            }
        });

        Dialog dialog = new Dialog("Create Lobby", skin) {
            @Override
            protected void result(Object object) {
                boolean ok = (Boolean) object;
                if (ok) {
                    Result result = controller.createLobby(
                        nameField.getText(),
                        privateBox.isChecked(),
                        passwordField.getText(),
                        visibleBox.isChecked()
                    );
                    showMessage(result.message());
                    if (result.success()) {
                        refreshLobbyList(controller.getLobbies());
                    }
                }
            }
        };

        dialog.getContentTable().add("Lobby Name:").row();
        dialog.getContentTable().add(nameField).width(200).row();
        dialog.getContentTable().add(privateBox).row();
        dialog.getContentTable().add(passwordField).width(200).row();
        dialog.getContentTable().add(visibleBox).row();

        dialog.button("Create", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }



    private void showPasswordDialog(LobbyData lobby) {
        Skin skin = AssetManager.getAssetManager().getSkin();
        TextField passField = new TextField("", skin);
        passField.setPasswordMode(true);
        passField.setPasswordCharacter('*');

        Dialog dialog = new Dialog("Enter Password", skin) {
            @Override
            protected void result(Object object) {
                boolean ok = (Boolean) object;
                if (ok) {
                    Result result = controller.joinLobby(lobby.getId(), passField.getText());
                    showMessage(result.message());
                    if (result.success()) {
                        refreshLobbyList(controller.getLobbies());
                    }
                }
            }
        };

        dialog.getContentTable().add(passField).width(200);
        dialog.button("Join", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
    }

    private void showMessage(String message) {
        Skin skin = AssetManager.getAssetManager().getSkin();
        Dialog dialog = new Dialog("Message", skin);
        dialog.text(message);
        dialog.button("OK");
        dialog.show(stage);
    }



    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
