package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;

public class ReactionView extends GameWindow {
    private final Table table = new Table(getSkin());
    private final TextButton laughButton = new TextButton(Player.Reaction.LAUGH.name(), getSkin());
    private final TextButton hiButton = new TextButton(Player.Reaction.HI.name(), getSkin());
    private final TextButton okButton = new TextButton(Player.Reaction.OK.name(), getSkin());
    private final TextButton likeButton = new TextButton(Player.Reaction.LIKE.name(), getSkin());
    private final TextButton dislikeButton = new TextButton(Player.Reaction.DISLIKE.name(), getSkin());
    private final TextButton heartButton = new TextButton(Player.Reaction.HEART.name(), getSkin());

    private final Player player;

    public ReactionView(Stage stage, Player player) {
        super("Reactions", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        this.player = Repository.getRepo().getUserByUsername(player.getUser().getUsername()).getPlayer();

        this.add(table).center().fill();

        this.setSize(500, 300);

        table.add(laughButton).pad(10).size(90, 70);
        table.add(hiButton).pad(10).size(90, 70);
        table.add(okButton).pad(10).size(90, 70).row();
        table.add(likeButton).pad(10).size(90, 70);
        table.add(dislikeButton).pad(10).size(90, 70);
        table.add(heartButton).pad(10).size(90, 70);

        laughButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addReaction(Player.Reaction.LAUGH);
            }
        });
        hiButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addReaction(Player.Reaction.HI);
            }
        });
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addReaction(Player.Reaction.OK);
            }
        });
        likeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addReaction(Player.Reaction.LIKE);
            }
        });
        dislikeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addReaction(Player.Reaction.DISLIKE);
            }
        });
        heartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.addReaction(Player.Reaction.HEART);
            }
        });
    }

    @Override
    public void update() {

    }
}
