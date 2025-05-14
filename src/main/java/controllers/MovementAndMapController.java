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
    MovementAndMapController(Repository repository) {super(repository);}

    @Override
    public Result handleCommand(String commandLine) {
        if (commandLine.matches(MovementAndMapCommands.WALK.getRegex())) {
            return handleWalk(commandLine);
        } else if (commandLine.matches(MovementAndMapCommands.PRINT_MAP.getRegex())) {
            return printMap(commandLine);
        } else if (commandLine.matches(MovementAndMapCommands.HELP_READING_MAP.getRegex())) {
            return printMapGuidance(commandLine);
        } else return new Result(false, "invalid command");
    }

    private Result handleWalk(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.WALK.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            int x = parseInt(matcher.group("X"));
            int y = parseInt(matcher.group("Y"));
            Player player = repo.getCurrentGame().getCurrentPlayer();
            int requiredEnergy = ReduceEnergy.calculateEnergy(player, new Position(x, y));
            return new Result(true, "required energy: " + requiredEnergy);
        }

        return new Result(false, "invalid command");
    }

    private Result walk(Position position) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        ReduceEnergy.movePlayer(player, position);
        return new Result(true, "-1");
    }

    private Result printMap(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.PRINT_MAP.getRegex());
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.matches()) {
            int x = parseInt(matcher.group("X"));
            int y = parseInt(matcher.group("Y"));
            int size = parseInt(matcher.group("size"));
            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.getFarm().printMap(x, y, size));
        } else return new Result(false, "invalid command");
    }

    private Result printMapGuidance(String commandLine) {
        Pattern pattern = Pattern.compile(MovementAndMapCommands.PRINT_MAP.getRegex());
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
}
