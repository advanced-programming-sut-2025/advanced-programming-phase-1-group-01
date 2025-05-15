package controllers;

import models.Item;
import models.Result;
import models.building.Building;
import models.character.player.Inventory;
import models.character.player.Slot;
import models.character.player.Player;
import models.crafting.CraftingRecipe;
import models.crafting.enums.CraftingRecipes;
import models.enums.BanItem;
import models.enums.commands.CraftingCommands;
import models.data.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CraftingController extends Controller {
    CraftingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {

        Player player = repo.getCurrentUser().getPlayer();
        Building cottage = player.getFarm().getCottage();
            if (!player.isPlayerNearBuilding(cottage)) {
            return new Result(false, "You are not near Cottage");
        }

        CraftingCommands matchedCommand = null;

        for (CraftingCommands cmd : CraftingCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if(matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case SHOW_RECIPE:
                return showRecipe();
            case CRAFT:
                return craft(command);
            case CHEAT_ADD_RECIPE:
                return cheatAddRecipe(command);
            case CHEAT_ADD_ITEM:
                return cheatAddItem(command);
        }
        return null;
    }

    private Result showRecipe() {
        StringBuilder info = new StringBuilder("Available Recipes:\n");

        for (CraftingRecipe recipe : repo.getCurrentUser().getPlayer().getCraftingRecipes()) {
            info.append("- ").append(recipe.name()).append("\n");
        }

        return new Result(true,info.toString());
    }

    private Result craft(String command) {
        String[] tokens = command.split(" ");
        String itemName = tokens[2];

        Set<CraftingRecipe> recipes = repo.getCurrentUser().getPlayer().getCraftingRecipes();

        CraftingRecipe targetRecipe = null;

        for (CraftingRecipe recipe : recipes) {
            if (recipe.name().equalsIgnoreCase(itemName)) {
                targetRecipe = recipe;
                break;
            }
        }

        if (targetRecipe == null) {
            return new Result(false, "Recipe not found.");
        }

        Map<String, Integer> requiredIngredients = targetRecipe.ingredients();

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialName = entry.getKey();
            int requiredAmount = entry.getValue();

            Slot inventorySlot = inventory.getSlot(materialName);
            int itemCount = inventorySlot.getQuantity();

            if (itemCount < requiredAmount) {
                return new Result(false, "You don't have enough " + materialName);
            }
        }

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialName = entry.getKey();
            int requiredAmount = entry.getValue();
            Slot inventorySlot = inventory.getSlot(materialName);
            inventorySlot.removeQuantity(requiredAmount);
        }

        player.getEnergy().consume(2);

            switch (itemName) {
                case "Cherry Bomb":
                    //return new CherryBomb();
                case "Bomb":
                    //return new Bomb();
                case "Mega Bomb":
                    //return new MegaBomb();
                case "Sprinkler":
                    //return new Sprinkler();
                case "Quality Sprinkler":
                    //return new QualitySprinkler();
                case "Iridium Sprinkler":
                    //return new IridiumSprinkler();
                case "Charcoal Klin":
                    //return new CharcoalKlin();
                case "Furnace":
                    //return new Furnace();
                case "Scarecrow":
                    //return new Scarecrow();
                case "Deluxe Scarecrow":
                    //return new DeluxeScarecrow();
                case "Bee House":
                    //return new BeeHouse();
                case "Cheese Press":
                    //return new CheesePress();
                case "Keg":
                    //return new Keg();
                case "Loom":
                    //return new Loom();
                case "Mayonnaise Machine":
                    //return new MayonnaiseMachine();
                case "Oil Maker":
                    //return new OilMaker();
                case "Preserves Jar":
                    //return new PreservesJar();
                case "Dehydrator":
                    //return new Dehydrator();
                case "Grass Starter":
                    //return new GrassStarter();
                case "Fish Smoker":
                    //return new FishSmoker();
                case "Mystic Tree Seed":
                    //return new MysticTreeSeed();
            }

        return new Result(true, "Crafted " + targetRecipe.name() + " successfully!");
    }

    private Result cheatAddRecipe(String command) {
        String[] tokens = command.split(" ");
        String recipeName = tokens[4];

        Player player = repo.getCurrentUser().getPlayer();

        CraftingRecipes matched = null;

        for (CraftingRecipes recipeEnum : CraftingRecipes.values()) {
            if (recipeEnum.name().equalsIgnoreCase(recipeName)) {
                matched = recipeEnum;
                break;
            }
        }

        if (matched == null) {
            return new Result(false, "Recipe \"" + recipeName + "\" does not exist.");
        }

        CraftingRecipe recipeToLearn = matched.toRecipe();
        player.addCraftingRecipe(recipeToLearn);

        return new Result(true, "Recipe added");
    }

    private Result cheatAddItem(String command) {
        String[] tokens = command.split(" ");
        String itemName = tokens[4];
        String itemCountStr = tokens[6];
        int itemCount = Integer.parseInt(itemCountStr);

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Item item = Inventory.getNewItem(itemName);

        if (item == null) {
            return new Result(false, "item not found");
        }

        if (BanItem.isBanItem(itemName)) {
            return new Result(false, "You can't add a ban item to your inventory");
        }

        if (player.getInventory().hasCapacity()) {
            return new Result(false, "inventory is full");
        }

        inventory.addItem(itemName, itemCount);
        return new Result(true, "Added " + itemCount + "x " + itemName + " to inventory.");
    }
}
