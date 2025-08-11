package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.TradeProposal;
import com.stardew_valley.models.TradeProposalService;

public class TradeHistory extends GameWindow{

    private Table table;
    private Label historyLabel;
    private TextButton backButton;
    private boolean isShown = true;
    private Runnable onBack;

    public TradeHistory(Stage stage) {
        super("Trade History", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        table = new Table(getSkin());
        historyLabel = new Label("", AssetManager.getAssetManager().getSkin());
        backButton = new TextButton("Back", AssetManager.getAssetManager().getSkin());

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
        table.add(historyLabel);
        table.row();
        table.add(backButton);
        table.center();
        table.setFillParent(true);
        addActor(table);
    }

    @Override
    public void update() {
        tradeHistory();
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

    private void tradeHistory() {
        TradeProposalService proposal = repo.getCurrentUser().getPlayer().getTradeProposalService();
        StringBuilder stringBuilder = new StringBuilder();
        for (TradeProposal tradeProposal: proposal.getProposals()) {
            stringBuilder.append("sender: ");
            stringBuilder.append(tradeProposal.getSenderUsername());
            stringBuilder.append(" ");
            stringBuilder.append("receiver: ");
            stringBuilder.append(tradeProposal.getReceiverUsername());
            stringBuilder.append(" ");
            stringBuilder.append("status: ");
            stringBuilder.append(tradeProposal.getStatus());
            stringBuilder.append("\n");
        }
        historyLabel.setText(stringBuilder.toString());
    }
}
