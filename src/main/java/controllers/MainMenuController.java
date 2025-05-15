package controllers;

import models.data.Repository;
import models.Result;
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

        return switch (matchedCommand) {
            case SHOW_CURRENT_MENU -> new Result(true, "now you are in main menu");
            case MENU_EXIT -> new Result(true, "now you are in login menu");
            case MENU_ENTER_GAME_MENU -> new Result(true, "now you are in game menu");
            case MENU_ENTER_PROFILE_MENU -> new Result(true, "now you are in profile menu");
            case USER_LOGOUT -> userLogout();
        };
    }

    private Result userLogout() {
        repo.setCurrentUser(null);
        return new Result(true, "You are logged out");
    }

}