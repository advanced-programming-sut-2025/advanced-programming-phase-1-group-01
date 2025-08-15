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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocialView extends GameWindow {
    private List<Table> friendshipsRows;
    private List<Label> friendNameLabels;
    private List<Label> friendshipLevelLabels;
    private List<ProgressBar> levelXpBars;
    private final Table friendshipTable;
    private final ScrollPane friendshipPane;

    {
        friendshipsRows = new ArrayList<>();
        friendNameLabels = new ArrayList<>();
        friendshipLevelLabels = new ArrayList<>();
        levelXpBars = new ArrayList<>();
        friendshipTable = new Table(getSkin());
        friendshipPane = new ScrollPane(friendshipTable);
        friendshipPane.setScrollingDisabled(true, false);
    }

    public SocialView(Stage stage) {
        super("Social", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        for (int i = 0; i < 7; i++) {
            friendNameLabels.add(new Label("", getSkin()));
            friendshipLevelLabels.add(new Label("", getSkin()));
            levelXpBars.add(new ProgressBar(0, 100, 1, false, getSkin()));

            Table friendshipRow = new Table(getSkin());
            friendshipRow.add(friendNameLabels.get(i)).left().padRight(30);
            friendshipRow.add(friendshipLevelLabels.get(i)).left().padRight(30);
            friendshipRow.add(levelXpBars.get(i)).width(200).left().padRight(30);

            friendshipsRows.add(friendshipRow);
        }

        this.add(friendshipPane);
    }

    @Override
    public void update() {
        if (!isVisible()) return;

        friendshipTable.clear();

        if (friendshipTable.isVisible()) {
            Map<com.stardew_valley.models.character.Character, Friendship> friendships = Repository.getRepo().getCurrentUser().getPlayer().getRelationService().getFriendships();
            List<com.stardew_valley.models.character.Character> friends = new ArrayList<>(friendships.keySet());

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

                friendshipTable.add(friendshipsRows.get(i)).fill().expand().pad(50);
                friendshipTable.row();
            }
        }
    }
}
