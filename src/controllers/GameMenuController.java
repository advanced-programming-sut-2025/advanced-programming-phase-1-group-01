package controllers;

import models.Result;
import models.data.Repository;

public class GameMenuController extends Controller {
    public GameMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result handleGameNewCommand(String... usernames) {
        return null;
    }

    private Result chooseGameMap(int mapNumber) {
        return null;
    }

    private Result loadGame() {
        return null;
    }

    private Result exitGame() {
        return null;
    }

    private Result deleteGame() {
        return null;
    }

    private Result handleNextTurn() {
        return null;
    }
}