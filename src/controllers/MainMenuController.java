package controllers;

import models.data.Repository;
import models.Result;

public class MainMenuController extends MenuController {
    MainMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result menuEnter(String menuName) {
        return null;
    }

    private Result userLogout() {
        return null;
    }

    
}