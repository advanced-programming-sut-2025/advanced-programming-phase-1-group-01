package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.stardew_valley.controllers.VotingController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.Voting;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.network.GameClient;

public class VotingView extends GameWindow {
    private final Stack stack = new Stack();

    private final Table startVotingTable = new Table(getSkin());
    private final TextField votingUsernameField = new TextField("", getSkin());
    private final TextButton startBanPlayerVotingButton = new TextButton("Ban Player", getSkin());
    private final TextButton startForceTerminateVotingButton = new TextButton("Force Terminate", getSkin());

    private final Table voteTable = new Table(getSkin());
    private final Label voteLabel = new Label("", getSkin());
    private final TextButton yesButton = new TextButton("YES", getSkin());
    private final TextButton noButton = new TextButton("NO", getSkin());

    public VotingView(Stage stage) {
        super("Voting", AssetManager.getAssetManager().getSkin(), "Letter", stage);

        startVotingTable.add(votingUsernameField).pad(20).colspan(2).row();
        startVotingTable.add(startBanPlayerVotingButton).pad(20);
        startVotingTable.add(startForceTerminateVotingButton).pad(20);
        stack.add(startVotingTable);

//        votingUsernameField.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                if (stage.getKeyboardFocus().equals(votingUsernameField)) {
//                    stage.setKeyboardFocus(null);
//                }
//            }
//        });

        startBanPlayerVotingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Result result = VotingController.startBanPlayerVoting(votingUsernameField.getText());
                GameView.setMessage(result.message());
                if (result.success())
                    GameClient.getInstance().startBanPlayerVoting(votingUsernameField.getText());
            }
        });
        startForceTerminateVotingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Result result = VotingController.startForceTerminateVoting();
                GameView.setMessage(result.message());
                if (result.success())
                    GameClient.getInstance().startForceTerminate();
            }
        });

        voteTable.add(voteLabel).pad(20).center().row();
        voteTable.add(yesButton).pad(20);
        voteTable.add(noButton).pad(20);
        stack.add(voteTable);
        voteTable.setVisible(false);

        yesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!VotingController.getCurrentVoting().vote(Repository.getRepo().getCurrentUser().getUsername(), Voting.Vote.YES)) {
                    GameView.setMessage("You already voted!");
                } else {
                    GameView.setMessage("Thanks for your vote.");
                    GameClient.getInstance().sendVote(Voting.Vote.YES);
                }
            }
        });
        noButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!VotingController.getCurrentVoting().vote(Repository.getRepo().getCurrentUser().getUsername(), Voting.Vote.NO)) {
                    GameView.setMessage("You already voted!");
                } else {
                    GameView.setMessage("Thanks for your vote.");
                    GameClient.getInstance().sendVote(Voting.Vote.NO);
                }
            }
        });

        this.add(stack);
    }

    @Override
    public void update() {
        startVotingTable.setVisible(!VotingController.isVoting());
        voteTable.setVisible(VotingController.isVoting());

        if (VotingController.isVoting())
            voteLabel.setText(VotingController.getCurrentVoting().type() == Voting.Type.BAN_PLAYER ? "Ban Player voting for " + VotingController.getCurrentVoting().getVotingUsername() : "Force Terminate Voting");
    }
}
