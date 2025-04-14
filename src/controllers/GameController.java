package controllers;

import models.Result;
import models.data.Repository;

public class GameController extends Controller {
    GameController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }
}