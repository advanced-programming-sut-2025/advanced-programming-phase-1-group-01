package controllers;

import models.Position;
import models.Result;
import models.building.Farm;
import models.character.player.Player;
import models.data.Repository;
import models.dateTime.Season;
import models.enums.commands.AnimalHusbandryCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HusbandryController extends Controller {
    HusbandryController(Repository repository) {super(repository);}

    @Override
    public Result handleCommand(String commandLine) {
        if (commandLine.matches(AnimalHusbandryCommands.BUILD.getRegex())) {
            return handleBuildShelter(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.BUY_ANIMAL.getRegex())) {
            return handleBuyAnimal(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.PET.getRegex())) {
            return handlePet(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.ANIMALS.getRegex())) {
            return handlePrintAnimals(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.SHEPHERD_ANIMAL.getRegex())) {
            return handleShepherd(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.FEED_HAY.getRegex())) {
            return handleFeedByHay(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.PRODUCES.getRegex())) {
            return handleGetFormattedProducts(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.COLLECT_PRODUCE.getRegex())) {
            return handleCollectProduce(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.SELL_ANIMAL.getRegex())) {
            return handleSellAnimal(commandLine);
        } else if (commandLine.matches(AnimalHusbandryCommands.CHEAT_CODE.getRegex())) {
            return handleFriendshipCheatCode(commandLine);
        } else return new Result(false, "Invalid command");
    }

    private Result handleBuildShelter(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.BUILD.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("animal");
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.buildShelter(new Position(x, y), animalName));
        } return new Result(false, "Invalid command");
    }

    private Result handleBuyAnimal(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.BUY_ANIMAL.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalType = matcher.group("animal");
            String animalName = matcher.group("name");

            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.buyAnimal(animalType, animalName));
        } else return new Result(false, "Invalid command");
    }

    private Result handlePet(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.PET.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.petAnimal(animalName));
        } else return new Result(false, "Invalid command");
    }

    private Result handlePrintAnimals(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.ANIMALS.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            Player player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.getFormattedAnimals());
        } else return new Result(false, "Invalid command");
    }

    private Result handleShepherd(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.SHEPHERD_ANIMAL.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.moveAnimal(animalName, new Position(x, y)));
        } else return new Result(false, "Invalid command");
    }

    private Result handleFeedByHay(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.FEED_HAY.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.feedByHay(animalName));
        } else return new Result(false, "Invalid command");
    }

    private Result handleGetFormattedProducts(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.PRODUCES.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.getFormattedProducts());
        } else return new Result(false, "Invalid command");
    }

    private Result handleCollectProduce(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.COLLECT_PRODUCE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            Player player = repo.getCurrentGame().getCurrentPlayer();
            Season season = repo.getCurrentGame().getTimeManager().getNow().getSeason();

            return new Result(true, farm.collectProduct(animalName, player, season));
        } else return new Result(false, "Invalid command");
    }

    private Result handleSellAnimal(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.SELL_ANIMAL.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.sellAnimal(animalName));
        } else return new Result(false, "Invalid command");
    }

    private Result handleFriendshipCheatCode(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.CHEAT_CODE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            int amount = Integer.parseInt(matcher.group("amount"));

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();

            return new Result(true, farm.friendshipCheatCode(animalName, amount));
        } else return new Result(false, "Invalid command");
    }
}
