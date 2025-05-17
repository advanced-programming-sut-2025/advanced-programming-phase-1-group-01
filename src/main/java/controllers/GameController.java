package controllers;

import controllers.ShopControllers.*;
import models.Result;
import models.data.Repository;
import models.enums.commands.*;
import models.shop.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController extends Controller {
    private final List<Command> commands;

    private final DateTimeController dateTimeController;
    private final EnergyController energyController;
    private final FarmingController farmingController;
    private final GameMenuController gameMenuController;
    private final RelationshipController relationshipController;
    private final ToolController toolController;
    private final WeatherController weatherController;
    private final MovementAndMapController movementAndMapController;
    private final InventoryController inventoryController;
    private final ArtisanController artisanController;
    private final CookingController cookingController;
    private final CraftingController craftingController;
    private final AnimalHusbandryController animalHusbandryController;
    private final NPCController npcController;
    private final SellController sellController;
    private final BlackSmithController blackSmithController;
    private final CarpenterController carpenterController;
    private final FishShopController fishShopController;
    private final JojaMartController jojaMartController;
    private final MarnieRanchController marnieRanchController;
    private final TheStardropSaloonController theStardropSaloonController;

    public GameController(Repository repo) {
        super(repo);
        dateTimeController = new DateTimeController(repo);
        energyController = new EnergyController(repo);
        farmingController = new FarmingController(repo);
        gameMenuController = new GameMenuController(repo);
        relationshipController = new RelationshipController(repo);
        toolController = new ToolController(repo);
        weatherController = new WeatherController(repo);
        movementAndMapController = new MovementAndMapController(repo);
        inventoryController = new InventoryController(repo);
        artisanController = new ArtisanController(repo);
        cookingController = new CookingController(repo);
        craftingController = new CraftingController(repo);
        animalHusbandryController = new AnimalHusbandryController(repo);
        npcController = new NPCController(repo);
        sellController = new SellController(repo);
        blackSmithController = new BlackSmithController(repo);
        carpenterController = new CarpenterController(repo);
        fishShopController = new FishShopController(repo);
        jojaMartController = new JojaMartController(repo);
        marnieRanchController = new MarnieRanchController(repo);
        theStardropSaloonController = new TheStardropSaloonController(repo);
        commands = new ArrayList<>();
        initCommands();
    }

    private void initCommands() {
        commands.addAll(Arrays.stream(DateTimeCommands.values()).toList());
        commands.addAll(Arrays.stream(EnergyCommands.values()).toList());
        commands.addAll(Arrays.stream(FarmingCommands.values()).toList());
        commands.addAll(Arrays.stream(GameMenuCommands.values()).toList());
        commands.addAll(Arrays.stream(RelationshipCommands.values()).toList());
        commands.addAll(Arrays.stream(ToolCommands.values()).toList());
        commands.addAll(Arrays.stream(WeatherCommands.values()).toList());
        commands.addAll(Arrays.stream(MovementAndMapCommands.values()).toList());
        commands.addAll(Arrays.stream(InventoryCommands.values()).toList());
        commands.addAll(Arrays.stream(AnimalHusbandryCommands.values()).toList());
        commands.addAll(Arrays.stream(NPCCommands.values()).toList());
        commands.addAll(Arrays.stream(SellCommands.values()).toList());
        commands.addAll(Arrays.stream(CookingCommands.values()).toList());
        commands.addAll(Arrays.stream(CraftingCommands.values()).toList());
        commands.addAll(Arrays.stream(ProcessingCommands.values()).toList());
        commands.addAll(Arrays.stream(BlackSmithCommands.values()).toList());
        commands.addAll(Arrays.stream(CarpenterCommands.values()).toList());
        commands.addAll(Arrays.stream(FishShopCommands.values()).toList());
        commands.addAll(Arrays.stream(JojaMartCommands.values()).toList());
        commands.addAll(Arrays.stream(MarnieCommands.values()).toList());
        commands.addAll(Arrays.stream(StardropSallonCommands.values()).toList());
    }

    @Override
    public Result handleCommand(String commandLine) {
        Command matchedCommand = null;

        for (Command command : commands) {
            if (commandLine.matches(command.getRegex())) {
                matchedCommand = command;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        if (matchedCommand instanceof DateTimeCommands) {
            return dateTimeController.handleCommand(commandLine);
        } else if (matchedCommand instanceof EnergyCommands) {
            return energyController.handleCommand(commandLine);
        } else if (matchedCommand instanceof FarmingCommands) {
            return farmingController.handleCommand(commandLine);
        } else if (matchedCommand instanceof GameMenuCommands) {
            return gameMenuController.handleCommand(commandLine);
        } else if (matchedCommand instanceof RelationshipCommands) {
            return relationshipController.handleCommand(commandLine);
        } else if (matchedCommand instanceof ToolCommands) {
            return toolController.handleCommand(commandLine);
        } else if (matchedCommand instanceof WeatherCommands) {
            return weatherController.handleCommand(commandLine);
        } else if (matchedCommand instanceof MovementAndMapCommands) {
            return movementAndMapController.handleCommand(commandLine);
        } else if (matchedCommand instanceof InventoryCommands) {
            return inventoryController.handleCommand(commandLine);
        } else if (matchedCommand instanceof AnimalHusbandryCommands) {
            return animalHusbandryController.handleCommand(commandLine);
        } else if (matchedCommand instanceof NPCCommands) {
            return npcController.handleCommand(commandLine);
        } else if (matchedCommand instanceof SellCommands) {
            return sellController.handleCommand(commandLine);
        } else if (matchedCommand instanceof CookingCommands) {
            return cookingController.handleCommand(commandLine);
        } else if (matchedCommand instanceof CraftingCommands) {
            return craftingController.handleCommand(commandLine);
        } else if (matchedCommand instanceof ProcessingCommands) {
            return artisanController.handleCommand(commandLine);
        } else if (matchedCommand instanceof BlackSmithCommands) {
        return blackSmithController.handleCommand(commandLine);
        } else if (matchedCommand instanceof CarpenterCommands) {
        return carpenterController.handleCommand(commandLine);
        } else if (matchedCommand instanceof FishShopCommands) {
        return fishShopController.handleCommand(commandLine);
        } else if (matchedCommand instanceof JojaMartCommands) {
        return jojaMartController.handleCommand(commandLine);
        } else if (matchedCommand instanceof MarnieCommands) {
        return marnieRanchController.handleCommand(commandLine);
        } else if (matchedCommand instanceof StardropSallonCommands) {
        return theStardropSaloonController.handleCommand(commandLine);
        }


        return new Result(false, "invalid command!");
    }
}