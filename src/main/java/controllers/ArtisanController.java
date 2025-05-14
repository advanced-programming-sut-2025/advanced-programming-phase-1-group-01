package controllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.enums.commands.ProcessingCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtisanController extends Controller {
    ArtisanController(Repository repository) {super(repository);}

    @Override
    public Result handleCommand(String commandLine) {
        if (commandLine.matches(ProcessingCommands.ARTISAN_USE.toString())) {
            return handleUse(commandLine);
        } else if (commandLine.matches(ProcessingCommands.ARTISAN_GET.getRegex())) {
            return handleGet(commandLine);
        } else return new Result(false, "Invalid command");
    }

    private Result handleUse(String commandLine) {
        Pattern pattern = Pattern.compile(ProcessingCommands.ARTISAN_USE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String artisanName = matcher.group("artisanName");
            String itemName = matcher.group("itemName");

            Player player = repo.getCurrentGame().getCurrentPlayer();
        }
    }

    private Result handleGet(String commandLine) {}




}
