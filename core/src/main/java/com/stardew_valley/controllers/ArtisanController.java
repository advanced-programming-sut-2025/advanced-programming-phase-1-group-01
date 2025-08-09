package com.stardew_valley.controllers;

import com.stardew_valley.models.Artisan;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.commands.ProcessingCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtisanController extends Controller {
    ArtisanController(Repository repository) {super(repository);}

    @Override
    public Result handleCommand(String commandLine) {
        if (commandLine.matches(ProcessingCommands.CHEAT_CODE.toString())) {
            return handleCheat(commandLine);
        } else return new Result(false, "Invalid command");
    }

    private Result handleUse(String commandLine) {
        Pattern pattern = Pattern.compile(ProcessingCommands.ARTISAN_USE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String artisanName = matcher.group("artisanName").toLowerCase();
            String itemName = matcher.group("itemName").toLowerCase();

            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.useArtisan(artisanName, itemName));

        } return new Result(false, "Invalid command");
    }

    private Result handleGet(String commandLine) {
        Pattern pattern = Pattern.compile(ProcessingCommands.ARTISAN_GET.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String artisanName = matcher.group("artisanName");

            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.getArtisan(artisanName));

        } else return new Result(false, "Invalid command");
    }

    private Result handleCheat(String commandLine) {
        Pattern pattern = Pattern.compile(ProcessingCommands.CHEAT_CODE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String id = matcher.group("id");

            Artisan artisan = repo.getCurrentGame().getCurrentPlayer().getArtisans().stream().filter(a -> a.getId() == Integer.parseInt(id)).findFirst().orElse(null);
            if (artisan != null) {
                artisan.finish();
                return new Result(true, "done");
            }
        }
        return new Result(false, "Invalid command");
    }



}
