package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
    private final List<Table> friendshipRows;
    private final Table friendshipTable;

    private final RelationshipService relationshipService;

    public FriendshipView(RelationshipService relationshipService) {
        super("Friendships", AssetManager.getAssetManager().getSkin(), "Letter");

        this.setSize(900, 600);
        this.setPosition(Gdx.graphics.getWidth() / 2f - 400, Gdx.graphics.getHeight() / 2f - 300);
        this.setMovable(true);
        this.setVisible(false);

        friendshipRows = new ArrayList<>();
        friendshipTable = new Table(getSkin());

        add(friendshipTable).expand().fill().padTop(30);

        this.relationshipService = relationshipService;

        setVisible(false);

        // temp code
        relationshipService.addFriend(Repository.getRepo().getUserByUsername("2").getPlayer());
        relationshipService.addFriend(Repository.getRepo().getUserByUsername("3").getPlayer());
        relationshipService.addFriend(Repository.getRepo().getUserByUsername("4").getPlayer());
    }

    @Override
    public void update() {
        friendshipTable.clear();

        for (Map.Entry<Character, Friendship> friendshipEntry : relationshipService.getFriendships().entrySet()) {
            Table friendshipRow = new Table(getSkin());

            if (friendshipEntry.getKey() instanceof NPC) continue;

            Player player = (Player) friendshipEntry.getKey();
            Friendship friendship = friendshipEntry.getValue();

            Label friendName = new Label("Friend: " + player.getUser().getUsername(), getSkin());
            Label friendshipLevel = new Label("Level: " + Integer.toString(friendship.getLevel()), getSkin());
            ProgressBar levelXpBar = new ProgressBar(0, friendship.getMaxXp(), 1, false, getSkin());
            levelXpBar.setValue(friendship.getXp());
            TextButton giftButton = new TextButton("Gift", getSkin());

            giftButton.getLabel().setFontScale(0.9f);

            friendshipRow.add(friendName).left().padRight(30);
            friendshipRow.add(friendshipLevel).left().padRight(30);
            friendshipRow.add(levelXpBar).width(200).left().padRight(30);
            friendshipRow.add(giftButton).size(100, 60).fill();

            friendshipTable.add(friendshipRow).pad(10);
            friendshipTable.row();
        }

        if (relationshipService.getFriendships().isEmpty()) {
            Label noFriendsLabel = new Label("YOU HAVE NO FRIENDS!", getSkin());
            friendshipTable.add(noFriendsLabel).center();
        }
    }
}
