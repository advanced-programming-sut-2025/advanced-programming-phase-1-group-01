package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.controllers.LobbyController;
import com.stardew_valley.models.*;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.network.GameClient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LobbyView extends ScreenAdapter implements InputProcessor {
    private final Stage stage;
    private final LobbyController controller;

    private Dialog playersDialog;
    private Table playersTable;
    private ScheduledExecutorService playersUpdateExecutor;

    //private static Thread thread;

    private Table lobbyTable;


    public LobbyView(LobbyController controller) {
        this.controller = controller;
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(new InputMultiplexer(stage));
        //startUpdateThread();
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
                List<LobbyData> recent2 = controller.loadRecentLobbies();
                refreshLobbyList(recent2);
            }
        });

        TextField searchField = new TextField("", skin);
        searchField.setMessageText("Enter Lobby ID");

        TextButton searchBtn = new TextButton("Search", skin);
        searchBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                String lobbyId = searchField.getText().trim();
                if (!lobbyId.isEmpty()) {
                    LobbyData lobby = controller.searchLobbyById(lobbyId);
                    if (lobby != null) {
                        showLobbyDialog(lobby);
                    } else {
                        showMessage("Lobby not found");
                    }
                }
            }
        });

        TextButton showPlayersBtn = new TextButton("Show Online Players", skin);
        showPlayersBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                showPlayersDialog(skin);
            }
        });



        lobbyTable = new Table(skin);
        ScrollPane scrollPane = new ScrollPane(lobbyTable, skin);


        root.top().pad(10);

        root.add(createLobbyBtn).pad(5);
        root.add(recentBtn).pad(5);
        root.add(showPlayersBtn).pad(5);
        root.row();

        root.add(searchField).width(200).pad(5);
        root.add(searchBtn).pad(5);
        root.row();

        root.add(scrollPane)
            .colspan(4)
            .expand()
            .fill()
            .padTop(10);


    }

    private void showPlayersDialog(Skin skin) {
        if (playersDialog == null) {
            playersDialog = new Dialog("Online Players", skin) {
                @Override
                public void result(Object object) {
                    if (playersUpdateExecutor != null) {
                        playersUpdateExecutor.shutdownNow();
                        playersUpdateExecutor = null;
                    }
                }
            };

            playersTable = new Table(skin);
            playersTable.pad(10).top();

            ScrollPane scrollPane = new ScrollPane(playersTable, skin);
            scrollPane.setFadeScrollBars(false);
            scrollPane.setScrollingDisabled(true, false);

            playersDialog.getContentTable().add(scrollPane).width(400).height(300);
            playersDialog.button("Close");

            playersDialog.show(stage);

            playersUpdateExecutor = Executors.newSingleThreadScheduledExecutor();
            playersUpdateExecutor.scheduleAtFixedRate(() -> {
                Gdx.app.postRunnable(this::updatePlayersList);
            }, 0, 2, TimeUnit.SECONDS);
        } else {
            playersDialog.show(stage);
        }
    }

    private void updatePlayersList() {
        playersTable.clear();

        Map<String, String> userToLobbyMap = new LinkedHashMap<>();

        for (LobbyData lobby : controller.getLobbiesForOnlinePlayers()) {
            for (User user : lobby.getPlayers()) {
                String username = user.getUsername();
                userToLobbyMap.put(username, lobby.getName());
            }
        }

        Skin skin = AssetManager.getAssetManager().getSkin();

        if (userToLobbyMap.isEmpty()) {
            Label noPlayerLabel = new Label("No players online", skin);
            noPlayerLabel.setColor(Color.RED);
            playersTable.add(noPlayerLabel).pad(10);
            return;
        }

        Label playerHeader = new Label("Player Name", skin);
        playerHeader.setFontScale(1.1f);
        playerHeader.setColor(Color.ORANGE);
        Label lobbyHeader = new Label("Lobby Name", skin);
        lobbyHeader.setFontScale(1.1f);
        lobbyHeader.setColor(Color.ORANGE);

        playersTable.add(playerHeader).left().pad(5).expandX();
        playersTable.add(lobbyHeader).left().pad(5).expandX();
        playersTable.row();

        boolean alternate = false;
        for (Map.Entry<String, String> entry : userToLobbyMap.entrySet()) {
            String username = entry.getKey();
            String lobbyName = entry.getValue();

            Label playerLabel = new Label(username, skin);
            Label lobbyLabel = new Label(lobbyName, skin);


            if (alternate) {
                playerLabel.setColor(Color.DARK_GRAY);
                lobbyLabel.setColor(Color.DARK_GRAY);
            } else {
                playerLabel.setColor(Color.WHITE);
                lobbyLabel.setColor(Color.WHITE);
            }
            alternate = !alternate;

            playersTable.add(playerLabel).left().pad(5).expandX();
            playersTable.add(lobbyLabel).left().pad(5).expandX();
            playersTable.row();
        }
    }



    private void refreshLobbyList(List<LobbyData> lobbyList) {
        lobbyTable.clear();

        if (lobbyList == null) {
            return;
        }

        for (LobbyData lobby : lobbyList) {
            if (!lobby.isVisible()) {
                continue;
            }
            lobbyTable.add(lobby.getName()).pad(5);
            lobbyTable.add(lobby.getPlayers().size() + " players").pad(5);

            TextButton leaveBtn = new TextButton("Leave", AssetManager.getAssetManager().getSkin());
            leaveBtn.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    GameClient.getInstance().leaveLobby(lobby.getId());
                }
            });

            TextButton joinBtn = new TextButton("Join", AssetManager.getAssetManager().getSkin());
            joinBtn.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    if (lobby.isPrivate()) {
                        showPasswordDialog(lobby);
                    } else {
                        refreshLobbyList(controller.getLobbies());
                        refreshLobbyList(controller.getLobbies());
                        Result result = controller.joinLobby(lobby.getId(), null);
                        showMessage(result.message());
                        refreshLobbyList(controller.getLobbies());
                        refreshLobbyList(controller.getLobbies());

                        if (result.success()) {
                            refreshLobbyList(controller.getLobbies());
                            //startGame(lobby);
                        }
                    }
                }
            });

            TextButton startBtn = new TextButton("Start", AssetManager.getAssetManager().getSkin());
            startBtn.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    if (lobby.getAdmin().getUsername().equals(Repository.getRepo().getCurrentUser().getUsername())) {
                        GameClient.getInstance().gameStart(lobby.getId());
                    }
                }
            });


            lobbyTable.add(joinBtn).pad(5);
            lobbyTable.add(startBtn).pad(5);
            lobbyTable.add(leaveBtn).pad(5);

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
                    int id = generateUniqueId();
                    Result result = controller.createLobby(
                        id,
                        nameField.getText(),
                        privateBox.isChecked(),
                        passwordField.getText(),
                        visibleBox.isChecked()
                    );
                    controller.joinLobby(id, passwordField.getText());
                    showMessage(result.message());
                    if (result.success()) {
                        refreshLobbyList(controller.getLobbies());
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


    private int generateUniqueId() {
        java.util.Random random = new Random();
        int id;
        do {
            id = 100000 + random.nextInt(900000);
        } while (findLobbyById(id) != null);
        return id;
    }

    public LobbyData findLobbyById(int id) {


        return controller.getLobbies().stream()
            .filter(l -> l.getId() == id)
            .findFirst()
            .orElse(null);
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

    public static void startGame(LobbyData lobby) {
        GameClient.getInstance().requestLobbyList(false);
        //System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee 1");
        List<Player> playerList = LobbyController.getInstance().findLobbyById(lobby.getId()).getPlayersReadyToPlay();
        String currentUsername = Repository.getRepo().getCurrentUser().getUsername();

        for (Player player : playerList) {
            if (player.getUser().getUsername().equals(currentUsername)) {
                Repository.getRepo().setCurrentUser(player.getUser());
                break;
            }
        }
        System.out.println(playerList.size());
        for (Player player : playerList) {
            System.out.println(player.getUser().getUsername() + " in startGame");
        }

        Game game = new Game(playerList);

        LobbyController.getInstance().getRepository().addGame(game);
        LobbyController.getInstance().getRepository().setCurrentGame(game);
        LobbyController.getInstance().getRepository().getCurrentUser().getPlayer().setPosition(new Position(80, 1280));

        Farm farm = FarmInitializer.initializeFarm();

        for (Player player : playerList) {
            player.setFarm(farm);
            player.setCurrentMap(farm);

        }
        //thread.interrupt();
        Main.getMain().setScreen(new GameView(new GameController(LobbyController.getInstance().getRepository())));
    }

    private void showLobbyDialog(LobbyData lobby) {
        Skin skin = AssetManager.getAssetManager().getSkin();
        Dialog dialog = new Dialog("Lobby Found", skin);

        Table contentTable = new Table(skin);


        contentTable.add(lobby.getName()).pad(5);
        contentTable.add(lobby.getPlayers().size() + " players").pad(5);
        contentTable.row();


        TextButton leaveBtn = new TextButton("Leave", skin);
        leaveBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameClient.getInstance().leaveLobby(lobby.getId());
                dialog.hide();
            }
        });


        TextButton joinBtn = new TextButton("Join", skin);
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
                        dialog.hide();
                    }
                }
            }
        });

        TextButton startBtn = new TextButton("Start", skin);
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (lobby.getAdmin().getUsername().equals(
                    Repository.getRepo().getCurrentUser().getUsername())) {
                    GameClient.getInstance().gameStart(lobby.getId());
                    dialog.hide();
                }
            }
        });

        contentTable.add(joinBtn).pad(5);
        contentTable.add(startBtn).pad(5);
        contentTable.add(leaveBtn).pad(5);
        contentTable.row();

        dialog.getContentTable().add(contentTable);
        dialog.button("Close");
        dialog.show(stage);
    }
}
