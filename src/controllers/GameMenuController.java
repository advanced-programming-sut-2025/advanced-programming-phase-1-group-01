package controllers;

import models.Game;
import models.Result;
import models.data.Repository;
import models.data.User;
import models.enums.commands.GameMenuCommands;
import models.enums.commands.ProfileMenuCommands;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenuController extends Controller {
    public GameMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        GameMenuCommands matchedCommand = null;

        for (GameMenuCommands cmd : GameMenuCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command!");
        }

        switch (matchedCommand) {
            case MENU_ENTER:
                return new Result(false,"You cannot navigate to other menus from here");

            case MENU_EXIT:
                return new Result(true,"now you are in main menu");

            case SHOW_CURRENT_MENU:
                return new Result(false,"now you are in game menu");

            case GAME_NEW:
                return handleGameNewCommand(command);

            case GAME_MAP:
        }
        return null;
    }

    private Result handleGameNewCommand(String command) {
        String usernamesString = extractValue(command,"-u",null);

            if (usernamesString.trim().isEmpty()) {
                return new Result(false,"No usernames provided");
            }

            String[] usernames = usernamesString.trim().split("\\s+");

            if (usernames.length > 3) {
                return new Result(false,"You must provide between 1 and 3 usernames.");
            }

            Set<String> usernameSet = new HashSet<>();

            for (String username : usernames) {
                User user = repo.getUserByUsername(username);

                if (user == null) {
                    return new Result(false,"Invalid username: " + username);
                }

                if (!usernameSet.add(username)) {
                    return new Result(false,"Duplicate username: " + username);
                }

                if (user.getGame() != null) {
                    return new Result(false,"User already in another game: " + username);
                }
            }
            new Game(usernameSet,);
            return new Result(true,"New game created successfully with users: " + String.join(", ", usernames));
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