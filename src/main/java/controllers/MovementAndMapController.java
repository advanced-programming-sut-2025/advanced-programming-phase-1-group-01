package controllers;

import models.Position;
import models.Result;
import models.building.TileType;
import models.character.player.Player;
import models.data.Repository;
import models.enums.Emoji;
import models.enums.commands.MovementAndMapCommands;
import models.moving.ReduceEnergy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class MovementAndMapController extends Controller {
    MovementAndMapController(Repository repository) {
        super(repository);
    }

    @Override
    public Result handleCommand(String commandLine) {
        if (commandLine.matches(MovementAndMapCommands.WALK.getRegex())) {
            return handleWalk(commandLine);
        } else if (commandLine.matches(MovementAndMapCommands.ANSWER_WALK.getRegex())) {
            return handleWalkAnswer(commandLine);
        } else if (commandLine.matches(MovementAndMapCommands.PRINT_MAP.getRegex())) {
            return printMap(commandLine);
        } else if (commandLine.matches(MovementAndMapCommands.HELP_READING_MAP.getRegex())) {
            return printMapGuidance(commandLine);
        } else if (commandLine.matches(MovementAndMapCommands.ENTER_OTHERS_ROOM.getRegex())) {
            return handleEnterRoom(commandLine);
        } else return new Result(false, "invalid command");
    }

    private static boolean isWalkUsed = false;
    private static Position walkPosition;

    private Result handleWalk(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.WALK.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            int x = parseInt(matcher.group("X"));
            int y = parseInt(matcher.group("Y"));
            walkPosition = new Position(x, y);
            Player player = repo.getCurrentGame().getCurrentPlayer();
            int requiredEnergy = ReduceEnergy.calculateEnergy(player, new Position(x, y));
            if (requiredEnergy == -1) return new Result(false, "invalid position");
            isWalkUsed = true;
            return new Result(true, "required energy: " + requiredEnergy + "\n" +
                    "Do you wanna go?");
        }

        return new Result(false, "invalid command");
    }

    private Result walk(Position position) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        ReduceEnergy.movePlayer(player, position, repo.getCurrentGame());
        isWalkUsed = false;
        return new Result(true, "walked to %s successfully".formatted(position));
    }

    private Result handleWalkAnswer(String commandLine) {
        if (isWalkUsed) {
            String answer = commandLine.substring(commandLine.indexOf("-a") + 2).trim();
            if (answer.equalsIgnoreCase("yes")) {
                return walk(walkPosition);
            } else if (answer.equalsIgnoreCase("no")) {
                return new Result(false, "walk failed");
            }
        }
        return new Result(false, "invalid command");
    }

    private Result printMap(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.PRINT_MAP.getRegex());
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.matches()) {
            int x = parseInt(matcher.group("X"));
            int y = parseInt(matcher.group("Y"));
            int size = parseInt(matcher.group("size"));
            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, repo.getCurrentGame().getCurrentMap().printMap(x, y, size, repo.getCurrentGame()));
        } else return new Result(false, "invalid command");
    }

    private Result printMapGuidance(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.HELP_READING_MAP.getRegex());
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.matches()) {
            StringBuilder output = new StringBuilder();
            output.append("Surfaces:\n");
            output.append(TileType.getFormattedGuidance());
            output.append("\n");
            output.append("Contents:\n");
            output.append(Emoji.getFormattedGuidance());
            return new Result(true, output.toString());
        } else return new Result(false, "invalid command");
    }

    private Result handleEnterRoom(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.ENTER_OTHERS_ROOM.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            int roomId = parseInt(matcher.group("roomNumber"));
            return new Result(true, repo.getCurrentGame().enterOthersRoom(roomId));
        } else return new Result(false, "invalid command");
    }
}
