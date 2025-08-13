package com.stardew_valley.controllers;

import com.stardew_valley.Main;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.views.GameView;
import com.stardew_valley.views.MainMenuView;

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
        Game game = repo.getCurrentGame();
        game.nextIndex();
        List<Player> players = game.getPlayers();
        int currIndex = players.indexOf(repo.getCurrentUser().getPlayer());
        int nextIndex = (currIndex + 1) % players.size();
        Player nextPlayer = players.get(nextIndex);
        repo.setCurrentUser(nextPlayer.getUser());
        game.setCurrentPlayer(nextPlayer);
        if (game.getCurrentIndex() % 4 == 0) {
            game.getTimeManager().getNow().advanceHour();
        }
        Main.getMain().setScreen(new GameView(new GameController(repo)));
        //hi
    }

    public void exitGame() {
        repo.setCurrentGame(null);
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
    }
}
