package com.stardew_valley.controllers;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.animal.Animal;
import com.stardew_valley.models.animal.ProductQuality;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.User;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.enums.commands.AnimalHusbandryCommands;
import com.stardew_valley.models.fish.Fish;
import com.stardew_valley.models.fish.FishInfo;
import com.stardew_valley.models.tool.FishingPole;
import com.stardew_valley.models.tool.Tool;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalHusbandryController extends Controller {
    AnimalHusbandryController(Repository repository) {super(repository);}

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
        User player = repo.getCurrentUser().getPlayer();

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.buildShelter(new Position(x, y), animalName));
        }

        return new Result(false, "Invalid command");
    }

    private Result handleBuyAnimal(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.BUY_ANIMAL.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalType = matcher.group("animal");
            String animalName = matcher.group("name");

            User player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.buyAnimal(animalType, animalName));
        } else return new Result(false, "Invalid command");
    }

    private Result handlePet(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.PET.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            User player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.petAnimal(animalName));
        } else return new Result(false, "Invalid command");
    }

    private Result handlePrintAnimals(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.ANIMALS.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            User player = repo.getCurrentGame().getCurrentPlayer();
            return new Result(true, player.getFormattedAnimals());
        } else return new Result(false, "Invalid command");
    }

    private Result handleShepherd(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.SHEPHERD_ANIMAL.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            int x = Integer.parseInt(matcher.group("X"));
            int y = Integer.parseInt(matcher.group("Y"));

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.moveAnimal(animalName, new Position(x, y)));
        } else return new Result(false, "Invalid command");
    }

    private Result handleFeedByHay(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.FEED_HAY.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            User player = repo.getCurrentGame().getCurrentPlayer();

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.feedByHay(animalName, player));
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
            User player = repo.getCurrentGame().getCurrentPlayer();
            Season season = repo.getCurrentGame().getTimeManager().getNow().getSeason();

            return new Result(true, farm.collectProduct(animalName, player, season));
        } else return new Result(false, "Invalid command");
    }

    private Result handleSellAnimal(String commandLine) {
        Pattern pattern = Pattern.compile(AnimalHusbandryCommands.SELL_ANIMAL.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String animalName = matcher.group("name");
            User player = repo.getCurrentGame().getCurrentPlayer();

            Farm farm = repo.getCurrentGame().getCurrentPlayer().getFarm();
            return new Result(true, farm.sellAnimal(animalName, player));
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

    private static final Random RANDOM = new Random();

    public Result fishing(String poleName) {
        User player = repo.getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Item item = inventory.getSlot(poleName).getItem();
        Season currSeason = repo.getCurrentGame().getTimeManager().getNow().getSeason();

        if (item == null) {
            return new Result(false, "item not found");
        }

        Tool tool;
        try {
            tool = (Tool) item;
        } catch (ClassCastException e) {
            return new Result(false, "not a tool!");
        }

        if (!(tool instanceof FishingPole)) {
            return new Result(false, "not a pole!");
        }
        FishingPole pole = (FishingPole) tool;

        // num of fishes: ⌈R × M × (skill + 2)⌉
        double R = RANDOM.nextDouble();
        int skill = player.getAbilityService().getFishing().getLevel();
        double M = repo.getCurrentGame().getWeatherManager().getTodayWeather().getFishingFactor();

        int numOfFishes = (int) Math.min(6, Math.ceil(R * M * (skill + 2)));

        R = RANDOM.nextDouble();
        double qualityNum = (R * (skill + 2) * pole.getInfo().getFishingFactor()) / (7 - numOfFishes);
        ProductQuality quality = Animal.getProductQuality(qualityNum);

        FishInfo fishType = FishInfo.getRandomFish(currSeason, player.getAbilityService().getFishing().isFull());
        inventory.addItem(fishType.getName(), numOfFishes);
        player.getAbilityService().getFishing().increaseXp(numOfFishes * 5);

        Slot slot = inventory.getSlot(fishType.getName());
        Fish fish = (Fish) slot.getItem();
        fish.setQuality(quality);

        return new Result(true, "%d of %s added to inventory".formatted(numOfFishes, fishType.getName()));
    }
}
