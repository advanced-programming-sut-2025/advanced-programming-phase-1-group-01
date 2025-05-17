package controllers;

import models.Item;
import models.Result;
import models.building.Building;
import models.character.player.Inventory;
import models.character.player.Slot;
import models.character.player.Player;
import models.crafting.*;
import models.crafting.enums.CraftingRecipes;
import models.enums.BanSellItem;
import models.enums.Direction;
import models.enums.commands.CraftingCommands;
import models.data.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case SHOW_RECIPE:
                return showRecipe();
            case CRAFT:
                return craft(command);
            case CHEAT_ADD_RECIPE:
                return cheatAddRecipe(command);
            case PLACE_ITEM:
                return placeItem(command);
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

        return new Result(true, info.toString());
    }

    private Result craft(String command) {
        String itemName = extractValue(command, "craft", null);

        Set<CraftingRecipe> recipes = repo.getCurrentUser().getPlayer().getCraftingRecipes();

        CraftingRecipe targetRecipe = null;

        for (CraftingRecipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(itemName)) {
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
            if (inventorySlot == null) {
                return new Result(false, "Slot not found.");
            }
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

        if (player.getEnergy().getAmount() == 0) {
            player.getEnergy().setHasPassedOut(true);
            repo.getCurrentGame().nextTurn();
        }

        inventory.addItem(itemName, 1);

        //repo.getCurrentGame().getCurrentPlayer().addCraftingDevices(itemName);

        return new Result(true, "Crafted " + targetRecipe.name() + " successfully!");
    }

    private Result cheatAddRecipe(String command) {
        String recipeName = extractValue(command, "-r", null);

        Player player = repo.getCurrentUser().getPlayer();

        CraftingRecipes matched = null;

        for (CraftingRecipes recipeEnum : CraftingRecipes.values()) {
            if (recipeEnum.getName().equalsIgnoreCase(recipeName)) {
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

    private Result placeItem(String command) {
        String itemName = extractValue(command, "-n", "-d");
        String directionStr = extractValue(command, "-d", null);

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Slot slot = inventory.getSlot(itemName);

        if (slot == null) {
            return new Result(false, "You don't have \"" + itemName + "\" in your inventory.");
        }

        Item item = slot.getItem();

        if (!(item instanceof CraftingDevice device)) {
            return new Result(false, item.getName() + " cannot be placed in farm.");
        }

        int x = player.getPosition().x();
        int y = player.getPosition().y();

        Direction direction = Direction.fromString(directionStr);

        if (direction == null) {
            return new Result(false, "Invalid direction: " + directionStr);
        }

        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
            case UP_LEFT -> {
                x--;
                y--;
            }
            case UP_RIGHT -> {
                x++;
                y--;
            }
            case DOWN_LEFT -> {
                x--;
                y++;
            }
            case DOWN_RIGHT -> {
                x++;
                y++;
            }
        }

        device.setPosition(x, y);
        device.setWorking(false);

        repo.getCurrentGame().getCurrentPlayer().getFarm().getTiles().get(x).get(y).setObject(device);
        repo.getCurrentGame().getCurrentPlayer().addCraftingDevices(device);

        slot.removeQuantity(1);

        return new Result(true, item.getName() + " placed successfully at (" + x + ", " + y + ").");
    }

    private Result cheatAddItem(String command) {
        String itemName = extractValue(command, "-n", "-c");
        String itemCountStr = extractValue(command, "-c", null);
        int itemCount = Integer.parseInt(itemCountStr);

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Item item = inventory.getNewItem(itemName);

        if (item == null) {
            return new Result(false, "item not found");
        

        if (player.getInventory().hasCapacity()) {
            return new Result(false, "inventory is full");
        }

        inventory.addItem(itemName, itemCount);
        return new Result(true, "Added " + itemCount + "x " + itemName + " to inventory.");
    }

    private String extractValue(String command, String startFlag, String endFlag) {
        String patternString;

        if (endFlag != null) {
            patternString = startFlag + " (.*?) " + endFlag;
        } else {
            patternString = startFlag + " (.*)";
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return null;
    }
}
