package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.controllers.TradeController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.TradeProposal;
import com.stardew_valley.models.TradeProposalService;
import com.stardew_valley.models.character.player.Player;

public class TradeMenuView extends GameWindow {
    private Table table;
    private TextButton startTradeButton;
    private TextButton tradeHistoryButton;
    private boolean isShown = false;

    private Runnable onTradeStart;
    private Runnable onTradeHistory;

    public TradeMenuView(Stage stage) {
        super("Trade Menu", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        table = new Table(getSkin());
        startTradeButton = new TextButton("Trade", getSkin());
        tradeHistoryButton = new TextButton("History", getSkin());

        startTradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setShown(false);
                setVisible(false);

                if (onTradeStart != null) {
                    onTradeStart.run();
                }
            }
        });

        tradeHistoryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setShown(false);
                setVisible(false);

                if (onTradeHistory != null) {
                    onTradeHistory.run();
                }
            }
        });

        table.add(startTradeButton).pad(15);
        table.row();
        table.add(tradeHistoryButton).pad(15);
        table.setFillParent(true);
        table.center();
        addActor(table);
    }

    @Override
    public void update() {

    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean isShown) {
        this.isShown = isShown;
    }

    public void setOnTradeStart(Runnable onTradeStart) {
        this.onTradeStart = onTradeStart;
    }

    public void setOnTradeHistory(Runnable onTradeHistory) {
        this.onTradeHistory = onTradeHistory;
    }
}
