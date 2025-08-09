package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.relations.Friendship;
import com.stardew_valley.models.relations.RelationshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FriendshipView extends GameWindow {
    private List<Table> friendshipsRows;
    private List<Label> friendNameLabels;
    private List<Label> friendshipLevelLabels;
    private List<ProgressBar> levelXpBars;
    private List<TextButton> giftButtons;

    private final Table friendshipTable;

    private final RelationshipService   relationshipService;

    public FriendshipView(RelationshipService relationshipService, Stage stage) {
        super("Friendships", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        friendshipsRows = new ArrayList<>();
        friendNameLabels = new ArrayList<>();
        friendshipLevelLabels = new ArrayList<>();
        levelXpBars = new ArrayList<>();
        giftButtons = new ArrayList<>();
        friendshipTable = new Table(getSkin());

        add(friendshipTable).expand().fill().padTop(30);

        this.relationshipService = relationshipService;

        setVisible(false);

        for (int i = 0; i < 3; i++) {
            friendNameLabels.add(new Label("", getSkin()));
            friendshipLevelLabels.add(new Label("", getSkin()));
            levelXpBars.add(new ProgressBar(0, 100, 1, false, getSkin()));
            TextButton giftButton = new TextButton("Gift", getSkin());
            giftButtons.add(giftButton);

            giftButton.getLabel().setFontScale(0.9f);

            Table friendshipRow = new Table(getSkin());
            friendshipRow.add(friendNameLabels.get(i)).left().padRight(30);
            friendshipRow.add(friendshipLevelLabels.get(i)).left().padRight(30);
            friendshipRow.add(levelXpBars.get(i)).width(200).left().padRight(30);
            friendshipRow.add(giftButtons.get(i)).size(100, 60).fill();

            friendshipsRows.add(friendshipRow);
        }

        // temp code
        if (repo.getCurrentUser().getUsername().equals("1"))
            relationshipService.addFriend(Repository.getRepo().getUserByUsername("2").getPlayer());
//        relationshipService.addFriend(Repository.getRepo().getUserByUsername("3").getPlayer());
//        relationshipService.addFriend(Repository.getRepo().getUserByUsername("4").getPlayer());
    }

    @Override
    public void update() {
        this.clear();

        Map<Character, Friendship> friendships = relationshipService.getFriendships();
        List<Character> friends = new ArrayList<>(friendships.keySet());

        for (int i = 0; i < Math.min(friendships.size(), 3); i++) {
            if (friends.get(i) instanceof NPC) continue;

            Player player = (Player) friends.get(i);
            Friendship friendship = friendships.get(friends.get(i));

            friendNameLabels.get(i).setText("Friend: " + player.getUser().getUsername());
            friendshipLevelLabels.get(i).setText("Level: " + friendship.getLevel());
            levelXpBars.get(i).setRange(0, friendship.getMaxXp());
            levelXpBars.get(i).setValue(friendship.getXp());

            giftButtons.get(i).addListener(new ClickListener() {
                 @Override
                 public void clicked (InputEvent event, float x, float y) {
                    setVisible(false);
                 }
            });

            add(friendshipsRows.get(i)).fill().expand();
            row();
        }
    }
}
