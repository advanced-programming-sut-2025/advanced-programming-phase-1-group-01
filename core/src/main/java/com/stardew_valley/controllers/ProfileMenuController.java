package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.commands.ProfileMenuCommands;
import com.stardew_valley.views.MainMenuView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController extends Controller {
    public ProfileMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        ProfileMenuCommands matchedCommand = null;

        for (ProfileMenuCommands cmd : ProfileMenuCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command!");
        }

//        return switch (matchedCommand) {
//            case MENU_ENTER -> new Result(false, "You cannot navigate to other menus from here");
//            case MENU_EXIT -> new Result(true, "now you are in main menu");
//            case SHOW_CURRENT_MENU -> new Result(true, "now you are in profile menu");
//            case CHANGE_USERNAME -> changeUsername(command);
//            case CHANGE_NICKNAME -> changeNickname(command);
//            case CHANGE_EMAIL -> changeEmail(command);
//            case CHANGE_PASSWORD -> changePassword(command);
//            case USER_INFO -> changeEmail(command);
//        };
        return null;
    }

    public String changeUsername(String newUsername) {
        User user = repo.getCurrentUser();

        if (user.getUsername().equals(newUsername)) {
            return "Please enter a new username";
        }

        if (repo.getUserByUsername(newUsername) != null) {
            return "This username is already taken";
        }

        if (!isUsernameValid(newUsername)) {
            return "New username format is invalid";
        }

        user.setUsername(newUsername);
        return "Your username changed to " + newUsername + " successfully";
    }

    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9\\-]+$");
    }

    public String changeNickname(String newNickname) {
        User user = repo.getCurrentUser();

        if (user.getNickname().equals(newNickname)) {
            return "Please enter a new nickname";
        }

        user.setNickname(newNickname);
        return "Your nickname changed to " + newNickname + " successfully";
    }

    public String changeEmail(String newEmail) {
        User user = repo.getCurrentUser();

        if (user.getEmail().equals(newEmail)) {
            return "Please enter a new email";
        }

        if (!isEmailValid(newEmail)) {
            return "Email format is invalid";
        }

        user.setEmail(newEmail);
        return "Your email changed to " + newEmail + " successfully";
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$");
    }

    public String changePassword(String newPassword) {
        User user = repo.getCurrentUser();

        if (user.getPassword().equals(newPassword)) {
            return "Please enter a new password different from the old one";
        }

        String validationError = isPasswordValid(newPassword);
        if (validationError != null) {
            return validationError;
        }

        user.setPassword(newPassword);
        return "Your password changed successfully";
    }

    private String isPasswordValid(String password) {
        if (password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter.";
        }
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit.";
        }
        if (!password.matches(".*[?><,\"';:\\\\|\\[\\]{}+=)(@*&^%$#!].*")) {
            return "Password must contain at least one special character.";
        }
        return null;
    }

    public void back(Label messageLabel) {
        messageLabel.setText("Back! Loading main menu...");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
            }
        },  //2
            0.01f
        );
    }
}
