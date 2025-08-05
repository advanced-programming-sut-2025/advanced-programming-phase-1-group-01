package com.stardew_valley.controllers;

import com.stardew_valley.Main;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.views.GameView;

import java.util.List;

public class SettingsController extends Controller {
    public SettingsController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    public void nextTurn() {
        List<Player> players = repo.getCurrentGame().getPlayers();
        int currIndex = players.indexOf(repo.getCurrentUser().getPlayer());
        int nextIndex = (currIndex + 1) % players.size();
        Player nextPlayer = players.get(nextIndex);
        repo.setCurrentUser(nextPlayer.getUser());
        repo.getCurrentGame().setCurrentPlayer(nextPlayer);
        Main.getMain().setScreen(new GameView(new GameController(Repository.getRepo())));
    }
}
