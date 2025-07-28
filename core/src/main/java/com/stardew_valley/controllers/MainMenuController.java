package com.stardew_valley.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.enums.commands.MainMenuCommands;
import com.stardew_valley.views.GameMenuView;
import com.stardew_valley.views.LoginMenuView;
import com.stardew_valley.views.ProfileMenuView;

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
//        return switch (matchedCommand) {
//            case SHOW_CURRENT_MENU -> new Result(true, "now you are in main menu");
//            case MENU_EXIT -> new Result(true, "now you are in login menu");
//            case MENU_ENTER_GAME_MENU -> new Result(true, "now you are in game menu");
//            case MENU_ENTER_PROFILE_MENU -> new Result(true, "now you are in profile menu");
//            case USER_LOGOUT -> userLogout();
//        };
        switch (matchedCommand) {
            case SHOW_CURRENT_MENU:
                return new Result(true, "now you are in main menu");
            case MENU_EXIT:
                return new Result(true, "now you are in login menu");
            case MENU_ENTER_GAME_MENU:
                return new Result(true, "now you are in game menu");
            case MENU_ENTER_PROFILE_MENU:
                return new Result(true, "now you are in profile menu");
            case USER_LOGOUT:
                return userLogout();
            default:
                throw new IllegalArgumentException("Unknown command: " + matchedCommand);
        }
    }

    private Result userLogout() {
        repo.setCurrentUser(null);
        return new Result(true, "You are logged out");
    }


    public void pregame(Label messageLabel) {
        messageLabel.setText("Loading pregame...");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new GameMenuView(new GameMenuController(repo)));
            }
        }, 2);
    }

    public void profileMenu(Label messageLabel) {
        messageLabel.setText("Loading Profile Menu...");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(repo)));
            }
        }, 2);
    }

    public void logout(Label messageLabel) {
        messageLabel.setText("logged out! Loading login menu...");
        repo.setCurrentUser(null);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(repo)));
            }
        }, 2);
    }

    public void exit(Label messageLabel) {
        messageLabel.setText("Bye!");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        }, 2);
    }

}
