package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.LoginMenuController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.TradeProposal;
import com.stardew_valley.network.GameClient;

import java.util.Optional;

public class TradeView extends GameWindow {
    private Table table;
    private SelectBox<String> playersBox;
    private TextButton selectPlayerButton;
    private TextButton backButton;
    private boolean isShown = true;
    private Runnable onBack;

    public TradeView(Stage stage) {
        super("start Trade", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        table = new Table(getSkin());
        playersBox = new SelectBox<>(getSkin());
        selectPlayerButton = new TextButton("Select", getSkin());
        playersBox.setItems("1", "2", "3", "4");
        backButton = new TextButton("Back", getSkin());

        selectPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                repo.getCurrentUser().getPlayer().getTradeProposalService().createProposal(repo.getCurrentUser().getUsername(), repo.getUserByUsername(playersBox.getSelected()).getUsername());
                repo.getCurrentUser().getPlayer().getTradeProposalService().setMessage("your trade request send to " + playersBox.getSelected());
                GameClient.getInstance().sendTradeRequest(repo.getUserByUsername(playersBox.getSelected()).getUsername());
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setShown(false);
                setVisible(false);

                if (onBack != null) {
                    onBack.run();
                }
            }
        });

        table.add(playersBox).pad(15);
        table.row();
        table.add(selectPlayerButton).pad(15);
        table.row();
        table.add(backButton).pad(15);
        table.row();
        table.center();
        table.setFillParent(true);
        addActor(table);
    }

    @Override
    public void update() {

    }

    public void setShown(boolean isShown) {
        this.isShown = isShown;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
    }
}
