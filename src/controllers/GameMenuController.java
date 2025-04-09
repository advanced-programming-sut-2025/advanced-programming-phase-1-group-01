// Created by: Amirhossein Shafiee
package controllers;

import models.Result;
import models.data.Repository;

<<<<<<<HEAD

public class GameMenuController extends MenuController {
    =======
public class GameMenuController extends Controller {
>>>>>>> 33882845e996bb5883ea1f22468cc5bc8a6c9dbc
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