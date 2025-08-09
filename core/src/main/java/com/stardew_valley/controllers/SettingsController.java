package com.stardew_valley.controllers;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.User;
import com.stardew_valley.models.data.Repository;

import java.io.IOException;
import java.util.List;

public class SettingsController extends Controller {
    public SettingsController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }
    public void nextTurn() throws IOException {
        Game game = repo.getCurrentGame();
        game.nextIndex();
        List<User> players = game.getPlayers();
        int currIndex = players.indexOf(repo.getCurrentUser().getPlayer());
        int nextIndex = (currIndex + 1) % players.size();
        User nextPlayer = players.get(nextIndex);
        repo.setCurrentUser(nextPlayer.getUser());
        game.setCurrentPlayer(nextPlayer);
        if (game.getCurrentIndex() % 4 == 0) {
            game.getTimeManager().getNow().advanceHour();
        }
        //Main.getMain().setScreen(new GameView(new GameController(repo)));
        //hi
    }
}
