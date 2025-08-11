package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.network.GameClient;

public class TradeRequest extends GameWindow {
    private Table table;
    private Player player;
    private Label senderUsername;
    private TextButton acceptButton;
    private TextButton rejectButton;

    public TradeRequest(Stage stage) {
        super("Trade Request", AssetManager.getAssetManager().getSkin(), "Letter", stage);
        table = new Table(getSkin());
        player = repo.getCurrentUser().getPlayer();
        senderUsername = new Label("", AssetManager.getAssetManager().getSkin());
        acceptButton = new TextButton("Accept", AssetManager.getAssetManager().getSkin());
        rejectButton = new TextButton("Reject", AssetManager.getAssetManager().getSkin());

        acceptButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameClient.getInstance().sendTradeResponse(player.getTradeRequester().getUsername(),true);
                player.getTradeProposalService().acceptProposal(player.getTradeRequester().getUsername(), player.getUser().getUsername());
                player.setTradeRequester(null);
            }
        });

        rejectButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameClient.getInstance().sendTradeResponse(player.getTradeRequester().getUsername(),false);
                player.getTradeProposalService().rejectProposal(player.getTradeRequester().getUsername(), player.getUser().getUsername());
                player.setTradeRequester(null);
            }
        });

        table.add(senderUsername).pad(15).padBottom(50);
        table.row();
        table.add(acceptButton).pad(15);
        table.add(rejectButton).pad(15);
        table.center();
        table.setFillParent(true);
        addActor(table);
    }

    @Override
    public void update() {
        if (player.getTradeRequester() != null) {
            senderUsername.setText("you have trade request from: " + player.getTradeRequester().getUsername());
            setVisible(true);
        }
        else {
            setVisible(false);
        }
    }
}
