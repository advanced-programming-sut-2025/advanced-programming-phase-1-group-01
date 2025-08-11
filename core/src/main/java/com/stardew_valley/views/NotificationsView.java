package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.MessageEntry;
import com.stardew_valley.models.character.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationsView extends GameWindow {
    private final List<Table> notifRows;
    private final List<Label> notifMessages;
    private final List<TextButton> seenButtons;

    {
        notifRows = new ArrayList<>();
        notifMessages = new ArrayList<>();
        seenButtons = new ArrayList<>();
    }

    private final Table notifsTable;

    private Map<MessageEntry, Boolean> notifsMap;
    private Player player;

    public NotificationsView(Stage stage) {
        super("Notifications", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        notifsTable = new Table(getSkin());
        notifsMap = repo.getCurrentUser().getPlayer().getNotifications();
        player = repo.getCurrentUser().getPlayer();

        add(notifsTable).expand().fill().padTop(30);

        setVisible(false);

        for (int i = 0; i < 10; i++) {
            notifMessages.add(new Label("", getSkin()));
            TextButton seenButton = new TextButton("Seen", getSkin());
            seenButtons.add(seenButton);

            Table notifRow = new Table(getSkin());

            notifRow.add(notifMessages.get(i)).left().padRight(30);
            notifRow.add(seenButton).size(100, 60).fill();

            notifRows.add(notifRow);
        }
    }

    @Override
    public void update() {
        notifsTable.clear();

        List<MessageEntry> unseenNotifs = new ArrayList<>();

        for (MessageEntry entry : notifsMap.keySet()) {
            if (!notifsMap.get(entry)) {
                unseenNotifs.add(entry);
            }
        }

        for (int i = 0; i < Math.min(unseenNotifs.size(), 10); i++) {
            MessageEntry notif = unseenNotifs.get(i);

            notifMessages.get(i).setText(notif.toString());

            seenButtons.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    player.readNotification(notif);
                }
            });

            notifsTable.add(notifRows.get(i)).fillX().pad(5);
            notifsTable.row();
        }

        setVisible(!unseenNotifs.isEmpty());
    }
}
