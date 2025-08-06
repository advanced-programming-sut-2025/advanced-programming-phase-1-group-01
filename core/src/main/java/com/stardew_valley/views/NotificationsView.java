package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.MessageEntry;
import com.stardew_valley.models.character.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationsView extends GameWindow {
    private final Table notifsTable;

    private Map<MessageEntry, Boolean> notifications;
    private Player player;

    public NotificationsView() {
        super("Notifications", AssetManager.getAssetManager().getSkin(), "Letter");

        notifsTable = new Table(getSkin());
        notifications = repo.getCurrentGame().getCurrentPlayer().getNotifications();
        player = repo.getCurrentGame().getCurrentPlayer();

        add(notifsTable).expand().fill().padTop(30);

        setVisible(false);

        repo.getCurrentGame().getCurrentPlayer().addNotification(repo.getUserByUsername("2").getPlayer(), "Hi!");
    }

    @Override
    public void update() {
        notifsTable.clear();

        List<MessageEntry> unseenNotifs = new ArrayList<>();

        for (MessageEntry entry : notifications.keySet()) {
            if (!notifications.get(entry)) {
                unseenNotifs.add(entry);
                setVisible(false);
            }
        }

        for (MessageEntry entry : notifications.keySet()) {
            final MessageEntry currentEntry = entry;

            Table notifRow = new Table(getSkin());

            Label notifMessage = new Label(entry.toString(), getSkin());
            TextButton seenButton = new TextButton("Seen", AssetManager.getAssetManager().getSkin());

            seenButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    notifications.put(currentEntry, true);
                    player.readNotification(currentEntry);
                }
            });
            seenButton.addListener(new ClickListener() {
                @Override
                public void clicked (InputEvent event, float x, float y) {
                    notifications.put(currentEntry, true);
                }
            });

            notifRow.add(notifMessage).left().padRight(30);
            notifRow.add(seenButton).size(100, 60).fill();

            notifsTable.add(notifRow).pad(10);
            notifsTable.row();
        }

        setVisible(!unseenNotifs.isEmpty());
    }
}
