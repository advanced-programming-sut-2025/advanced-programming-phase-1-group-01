package controllers;

import models.Item;
import models.Result;
import models.animal.Animal;
import models.animal.ProductQuality;
import models.character.player.Inventory;
import models.character.player.Player;
import models.character.player.Slot;
import models.data.Repository;
import models.dateTime.Season;
import models.fish.Fish;
import models.fish.FishInfo;
import models.tool.FishingPole;
import models.tool.Tool;

import java.util.Random;

public class AnimalController extends Controller {
    AnimalController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private static final Random RANDOM = new Random();

    public Result fishing(String poleName) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
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
        Slot slot = inventory.getSlot(fishType.getName());
        Fish fish = (Fish) slot.getItem();
        fish.setQuality(quality);

        return new Result(true, "%d of %s added to inventory".formatted(numOfFishes, fishType.getName()));
    }
}