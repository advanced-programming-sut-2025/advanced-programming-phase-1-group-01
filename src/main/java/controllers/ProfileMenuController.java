package controllers;

import models.Result;
import models.data.Repository;
import models.data.User;
import models.enums.commands.ProfileMenuCommands;

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

        return switch (matchedCommand) {
            case MENU_ENTER -> new Result(false, "You cannot navigate to other menus from here");
            case MENU_EXIT -> new Result(true, "now you are in main menu");
            case SHOW_CURRENT_MENU -> new Result(true, "now you are in profile menu");
            case CHANGE_USERNAME -> changeUsername(command);
            case CHANGE_NICKNAME -> changeNickname(command);
            case CHANGE_EMAIL -> changeEmail(command);
            case CHANGE_PASSWORD -> changePassword(command);
            case USER_INFO -> showUserInfo();
        };
    }

    private Result changeUsername(String command) {
        String username = extractValue(command,"-u",null);
        User user = repo.getCurrentUser();

        if (user.getUsername().equals(username)) {
            return new Result(false, "Please enter a new username");
        }

        if (repo.getUserByUsername(username) != null) {
            return new Result(false, "This username is already taken");
        }

        if (!isUsernameValid(username)) {
            return new Result(false, "new username format is invalid");
        }

        user.setUsername(username);
        return new Result(true, "your username changed to " + username + " successfully");

    }

    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9\\-]+$");
    }

    private Result changeNickname(String command) {
        String nickname = extractValue(command,"-n",null);
        User user = repo.getCurrentUser();

        if (user.getNickname().equals(nickname)) {
            return new Result(false, "Please enter a new nickname");
        }

        user.setNickname(nickname);
        return new Result(true, "your nickname changed to " + nickname + " successfully");
    }

    private Result changeEmail(String command) {
        String email = extractValue(command,"-e",null);
        User user = repo.getCurrentUser();

        if (user.getEmail().equals(email)) {
            return new Result(false, "Please enter a new email");
        }

        if (!isEmailValid(email)) {
            return new Result(false, "email format is invalid");
        }

        user.setEmail(email);
        return new Result(true, "your email changed to " + email + " successfully");
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$");
    }

    private Result changePassword(String command) {
        String newPassword = extractValue(command,"-p","-o");
        String oldPassword = extractValue(command,"-o",null);

        User user = repo.getCurrentUser();

        if (!user.getPassword().equals(oldPassword)) {
            return new Result(false, "Password is incorrect");
        }

        if (oldPassword.equals(newPassword)) {
            return new Result(false, "please enter a new password");
        }

        if (isPasswordValid(newPassword) != null) {
            return new Result(false, "Password format is invalid");
        }

        user.setPassword(newPassword);
        return new Result(true, "your password changed to " + newPassword + " successfully");
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
        if (!password.matches("[?><,\"';:\\\\/|\\[\\]{}+=)(*&^%$#!-]")) {
            return "Password must contain at least one special character.";
        }
        return null;
    }

    private Result showUserInfo() {
        User user = repo.getCurrentUser();
        String info = "username : " + user.getUsername() + "\n" +
                "nickname : " + user.getNickname() + "\n" +
                "highest amount of money" + user.getHighestEarnedBalance() + "\n" +
                "number of played games : " + user.getNumOfPlayedGames();

        return new Result(true,info);
    }

    private String extractValue(String command, String startFlag, String endFlag) {
        String patternString;

        if (endFlag != null) {
            patternString = startFlag + " (.*?) " + endFlag;
        }

        else {
            patternString = startFlag + " (.*)";
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return null;
    }
}
