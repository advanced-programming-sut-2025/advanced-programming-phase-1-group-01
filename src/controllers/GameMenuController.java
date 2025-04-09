// Created by: Amirhossein Shafiee
package controllers;

import models.Result;
import models.data.Repository;

public class GameMenuController extends MenuController {
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

    private Result handleGameMapCommand(int mapNumber) {
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


}