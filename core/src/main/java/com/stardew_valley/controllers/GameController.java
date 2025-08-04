package com.stardew_valley.controllers;

import com.stardew_valley.controllers.ShopControllers.*;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.commands.*;
import com.stardew_valley.models.shop.enums.*;

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
    private final ShippingBinController sellController;
    private final BlackSmithController blackSmithController;
    private final CarpenterController carpenterController;
    private final FishShopController fishShopController;
    private final JojaMartController jojaMartController;
    private final MarnieRanchController marnieRanchController;
    private final TheStardropSaloonController theStardropSaloonController;
    private final PierreGeneralStoreController pierreGeneralStoreController;

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
        sellController = new ShippingBinController(repo);
        blackSmithController = new BlackSmithController(repo);
        carpenterController = new CarpenterController(repo);
        fishShopController = new FishShopController(repo);
        jojaMartController = new JojaMartController(repo);
        marnieRanchController = new MarnieRanchController(repo);
        theStardropSaloonController = new TheStardropSaloonController(repo);
        pierreGeneralStoreController = new PierreGeneralStoreController(repo);
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
        commands.addAll(Arrays.stream(PierreCommands.values()).toList());
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

//        return switch (matchedCommand) {
//            case null -> new Result(false, "invalid command");
//            case DateTimeCommands dateTimeCommands -> dateTimeController.handleCommand(commandLine);
//            case EnergyCommands energyCommands -> energyController.handleCommand(commandLine);
//            case FarmingCommands farmingCommands -> farmingController.handleCommand(commandLine);
//            case GameMenuCommands gameMenuCommands -> gameMenuController.handleCommand(commandLine);
//            case RelationshipCommands relationshipCommands -> relationshipController.handleCommand(commandLine);
//            case ToolCommands toolCommands -> toolController.handleCommand(commandLine);
//            case WeatherCommands weatherCommands -> weatherController.handleCommand(commandLine);
//            case MovementAndMapCommands movementAndMapCommands -> movementAndMapController.handleCommand(commandLine);
//            case InventoryCommands inventoryCommands -> inventoryController.handleCommand(commandLine);
//            case AnimalHusbandryCommands animalHusbandryCommands ->
//                    animalHusbandryController.handleCommand(commandLine);
//            case NPCCommands npcCommands -> npcController.handleCommand(commandLine);
//            case SellCommands sellCommands -> sellController.handleCommand(commandLine);
//            case CookingCommands cookingCommands -> cookingController.handleCommand(commandLine);
//            case CraftingCommands craftingCommands -> craftingController.handleCommand(commandLine);
//            case ProcessingCommands processingCommands -> artisanController.handleCommand(commandLine);
//            case BlackSmithCommands blackSmithCommands -> blackSmithController.handleCommand(commandLine);
//            case CarpenterCommands carpenterCommands -> carpenterController.handleCommand(commandLine);
//            case FishShopCommands fishShopCommands -> fishShopController.handleCommand(commandLine);
//            case JojaMartCommands jojaMartCommands -> jojaMartController.handleCommand(commandLine);
//            case MarnieCommands marnieCommands -> marnieRanchController.handleCommand(commandLine);
//            case StardropSallonCommands stardropSallonCommands ->
//                    theStardropSaloonController.handleCommand(commandLine);
//            case PierreCommands pierreCommands -> pierreGeneralStoreController.handleCommand(commandLine);
//            default -> new Result(false, "invalid command!!!!");
//        };

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        } else if (matchedCommand instanceof DateTimeCommands) {
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
        } else if (matchedCommand instanceof PierreCommands) {
            return pierreGeneralStoreController.handleCommand(commandLine);
        } else {
            return new Result(false, "invalid command!!!!");
        }



    }

    public DateTimeController getDateTimeController() {
        return this.dateTimeController;
    }

    public EnergyController getEnergyController() {
        return this.energyController;
    }
}
