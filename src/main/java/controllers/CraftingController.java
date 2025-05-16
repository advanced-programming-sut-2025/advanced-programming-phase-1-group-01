package controllers;

import models.Item;
import models.Result;
import models.building.Building;
import models.character.player.Inventory;
import models.character.player.Slot;
import models.character.player.Player;
import models.crafting.*;
import models.crafting.enums.CraftingRecipes;
import models.enums.BanItem;
import models.enums.Direction;
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

        if (player.getEnergy().getAmount() == 0) {
            player.getEnergy().setHasPassedOut(true);
            repo.getCurrentGame().nextTurn();
        }

        inventory.addItem(itemName,1);

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

    private Result placeItem(String command) {
        String[] tokens = command.split(" ");
        String itemName = tokens[3];
        String directionStr = tokens[5];

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
            case UP_LEFT -> { x--; y--; }
            case UP_RIGHT -> { x++; y--; }
            case DOWN_LEFT -> { x--; y++; }
            case DOWN_RIGHT -> { x++; y++; }
        }

        device.setPosition(x, y);
        device.setWorking(false);

        repo.getCurrentGame().getCurrentPlayer().getFarm().getTiles().get(x).get(y).setObject(device);

        slot.removeQuantity(1);

        return new Result(true, item.getName() + " placed successfully at (" + x + ", " + y + ").");
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
