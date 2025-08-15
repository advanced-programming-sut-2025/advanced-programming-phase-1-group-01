package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.controllers.RelationshipController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.relations.Friendship;
import com.stardew_valley.models.relations.RelationshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.Gdx.input;

public class FriendshipView extends GameWindow {
    private final Stack stack;

    private List<Table> friendshipsRows;
    private List<Label> friendNameLabels;
    private List<Label> friendshipLevelLabels;
    private List<ProgressBar> levelXpBars;
    private List<TextButton> giftButtons;
    private List<TextButton> chatButtons;
        private final Table friendshipTable;
        private final ScrollPane friendshipPane;

    private final Table chatTable;
    private final Label messagesLabel;
    private final ScrollPane messagesPane;
    private final TextField messageField;
    private final TextButton sendButton;
    private final TextButton backButton;
    private Player chatFriend;

    private final GiftView giftView;
    private final RelationshipService relationshipService;
    private final RelationshipController controller;

    public FriendshipView(RelationshipController controller, RelationshipService relationshipService, Stage stage, GiftView giftView) {
        super("Friendships", AssetManager.getAssetManager().getSkin(), "Letter", stage);
        this.giftView = giftView;
        this.controller = controller;

        stack = new Stack();

        friendshipsRows = new ArrayList<>();
        friendNameLabels = new ArrayList<>();
        friendshipLevelLabels = new ArrayList<>();
        levelXpBars = new ArrayList<>();
        giftButtons = new ArrayList<>();
        chatButtons = new ArrayList<>();
        friendshipTable = new Table(getSkin());
        friendshipPane = new ScrollPane(friendshipTable);
        friendshipPane.setScrollingDisabled(true, false);

        chatTable = new Table(getSkin());
        messagesLabel = new Label("", getSkin());
        messagesPane = new ScrollPane(messagesLabel, getSkin());
        messagesPane.setScrollingDisabled(true, false);
        messageField = new TextField("", getSkin());
        sendButton = new TextButton("send", getSkin());
        backButton = new TextButton(">", getSkin());

        stack.add(friendshipPane);

        chatTable.add(backButton).size(90, 70).expandX().right().padBottom(10).row();
        chatTable.add(messagesPane).size(600, 380).center().colspan(2).padBottom(20);
        chatTable.row();
        chatTable.add(messageField).size(500, 80).fillX().expandX().left();
        chatTable.add(sendButton).height(80).expandX().right();
        stack.add(chatTable);
        chatTable.setVisible(false);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                friendshipTable.setVisible(true);
                chatTable.setVisible(false);
                stage.setKeyboardFocus(null);
            }
        });

        messageField.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.setKeyboardFocus(messageField);
            }
        });

        this.relationshipService = relationshipService;

        setVisible(false);

        for (int i = 0; i < 7; i++) {
            friendNameLabels.add(new Label("", getSkin()));
            friendshipLevelLabels.add(new Label("", getSkin()));
            levelXpBars.add(new ProgressBar(0, 100, 1, false, getSkin()));
            TextButton giftButton = new TextButton("Gift", getSkin());
            giftButtons.add(giftButton);
            TextButton chatButton = new TextButton("chat", getSkin());
            chatButtons.add(chatButton);

            chatButton.getLabel().setFontScale(0.9f);
            giftButton.getLabel().setFontScale(0.9f);

            Table friendshipRow = new Table(getSkin());
            friendshipRow.add(friendNameLabels.get(i)).left().padRight(30);
            friendshipRow.add(friendshipLevelLabels.get(i)).left().padRight(30);
            friendshipRow.add(levelXpBars.get(i)).width(200).left().padRight(30);
            friendshipRow.add(giftButtons.get(i)).size(100, 60).left().padRight(30);
            friendshipRow.add(chatButtons.get(i)).size(100, 60).fill();

            friendshipsRows.add(friendshipRow);
        }

        this.add(stack);

        for (User user : Repository.getRepo().getUsers().values()) {
            if (!user.getUsername().equals(Repository.getRepo().getCurrentUser().getUsername())) {
                relationshipService.addFriend(user.getPlayer());
            }
        }

        relationshipService.addFriend(Repository.getRepo().getCurrentGame().getFarm().getNPCs().get(0));
        relationshipService.addFriend(Repository.getRepo().getCurrentGame().getFarm().getNPCs().get(1));
        relationshipService.addFriend(Repository.getRepo().getCurrentGame().getFarm().getNPCs().get(2));
        relationshipService.addFriend(Repository.getRepo().getCurrentGame().getFarm().getNPCs().get(3));
    }

    @Override
    public void update() {
        if (!isVisible()) return;

        this.clear();
        friendshipTable.clear();

        if (friendshipTable.isVisible()) {
            Map<Character, Friendship> friendships = relationshipService.getFriendships();
            List<Character> friends = new ArrayList<>(friendships.keySet());

            for (int i = 0; i < Math.min(friendships.size(), 7); i++) {
                Character character = friends.get(i);
                Friendship friendship = friendships.get(character);

                String name;
                if (character instanceof Player) {
                    name = ((Player) character).getUser().getUsername();
                } else if (character instanceof NPC) {
                    name = ((NPC) character).getType().getName();
                } else {
                    continue;
                }

                friendNameLabels.get(i).setText("Friend: " + name);
                friendshipLevelLabels.get(i).setText("Level: " + friendship.getLevel());
                levelXpBars.get(i).setRange(0, friendship.getMaxXp());
                levelXpBars.get(i).setValue(friendship.getXp());

                giftButtons.get(i).clearListeners();
                giftButtons.get(i).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        setVisible(false);
                        giftView.setVisible(true);
                        giftView.setFriend(character);
                    }
                });

                if (character instanceof Player) {
                    chatButtons.get(i).clearListeners();
                    chatButtons.get(i).setVisible(true);
                    chatButtons.get(i).addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            chatFriend = (Player) character;
                            friendshipTable.setVisible(false);
                            chatTable.setVisible(true);
                            stage.setKeyboardFocus(messageField);
                        }
                    });
                } else {
                    chatButtons.get(i).setVisible(false);
                }

                friendshipTable.add(friendshipsRows.get(i)).fill().expand().pad(50);
                friendshipTable.row();
            }
        }

        if (chatTable.isVisible()) {
            if (input.isKeyPressed(Input.Keys.ENTER)) {
                if (messageField.getText().isBlank()) return;
                String message = messageField.getText();
                Result result = controller.talk(chatFriend.getUser().getUsername(), message);
                GameView.setMessage(result.message());
                messageField.setText("");
            }

            stage.setKeyboardFocus(messageField);

            Result result = controller.talkHistory(chatFriend.getUser().getUsername());
            messagesLabel.setText(result.message());

            sendButton.clearListeners();
            sendButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (messageField.getText().isBlank()) return;
                    String message = messageField.getText();
                    Result result = controller.talk(chatFriend.getUser().getUsername(), message);
                    GameView.setMessage(result.message());
                    messageField.setText("");
                }
            });
        }
        add(stack);
    }
}
