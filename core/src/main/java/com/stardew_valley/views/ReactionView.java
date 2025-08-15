package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.ReactionType;
import com.stardew_valley.network.GameClient;

public class ReactionView extends GameWindow {

    private final Table reactionsTable = new Table(getSkin());
    //private final Player player;

    private final ReactionType[] defaultReactions = {
        ReactionType.LAUGH, ReactionType.LIKE, ReactionType.DISLIKE,
        ReactionType.ANGER, ReactionType.LOVE, ReactionType.HI
    };

    private int indexToChange = 0;

    public ReactionView(Stage stage) {
        super("Reactions", AssetManager.getAssetManager().getSkin(), "Letter", stage);


        this.setSize(600, 400);

        updateReactionButtons();
        Table rootTable = new Table(getSkin());
        rootTable.add(reactionsTable).colspan(3).padBottom(10).row();

        TextField messageField = new TextField("", getSkin());
        messageField.setMessageText("Max 10 chars");
        messageField.setMaxLength(10);

        TextButton sendTextButton = new TextButton("Send", getSkin());
        sendTextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String msg = messageField.getText().trim();
                if (!msg.isEmpty() && msg.length() <= 10) {
                    Repository.getRepo().getCurrentUser().getPlayer().setReactionText(msg);
                    messageField.setText("");
                    GameClient.getInstance().sendReactionToServer(Repository.getRepo().getCurrentUser().getUsername(), true, msg, 0);
                }
            }
        });

        Table msgTable = new Table(getSkin());
        msgTable.add(messageField).width(200).pad(5);
        msgTable.add(sendTextButton).pad(5);

        rootTable.add(msgTable).colspan(3).padTop(10).row();

        TextButton changeDefaultsButton = new TextButton("Change Reactions", getSkin());
        changeDefaultsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showChangeDialog();
            }
        });

        rootTable.add(changeDefaultsButton).colspan(3).padTop(10).row();

        ScrollPane scrollPane = new ScrollPane(rootTable, getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);

        this.add(scrollPane).expand().fill();
    }

    private void updateReactionButtons() {
        reactionsTable.clear();
        for (int i = 0; i < defaultReactions.length; i++) {
            ReactionType rt = defaultReactions[i];
            TextButton btn = new TextButton(rt.name(), getSkin());
            btn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Repository.getRepo().getCurrentUser().getPlayer().getReactionUI().setStarted(rt);
                    GameClient.getInstance().sendReactionToServer(Repository.getRepo().getCurrentUser().getUsername(), false, "", ReactionType.toId(rt));
                }
            });
            reactionsTable.add(btn).pad(10).size(90, 70);
            if ((i + 1) % 3 == 0) reactionsTable.row();
        }
    }

    private void showChangeDialog() {
        Dialog dialog = new Dialog("Select Reaction", getSkin());

        Table content = new Table(getSkin());
        for (ReactionType rt : ReactionType.values()) {
            TextButton btn = new TextButton(rt.name(), getSkin());
            btn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    defaultReactions[indexToChange] = rt;
                    indexToChange = (indexToChange + 1) % defaultReactions.length;
                    updateReactionButtons();
                    dialog.hide();
                }
            });
            content.add(btn).pad(5).row();
        }

        ScrollPane scrollPane = new ScrollPane(content, getSkin());
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        dialog.getContentTable().add(scrollPane).width(300).height(250);
        dialog.button("Cancel");
        dialog.show(getStage());
    }

    @Override
    public void update() {}
}
