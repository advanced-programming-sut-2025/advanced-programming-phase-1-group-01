package controllers;

import models.data.Repository;
import models.Result;
import models.enums.commands.GameMenuCommands;
import models.enums.commands.MainMenuCommands;

public class MainMenuController extends Controller {
    public MainMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        MainMenuCommands matchedCommand = null;

        for (MainMenuCommands cmd : MainMenuCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case SHOW_CURRENT_MENU:
                return new Result(true,"now you are in main menu");

            case MENU_EXIT:
                return new Result(true,"now you are in login menu");

            case MENU_Enter_GAME_MENU:
                return new Result(true,"now you are in game menu");

            case MENU_ENTER_PROFILE_MENU:
                return new Result(true,"now you are in profile menu");

            case USER_LOGOUT:
                return userLogout();
        }
        return null;
    }

    private Result userLogout() {
        repo.setCurrentUser(null);
        return new Result(true, "You are logged out");
    }

}