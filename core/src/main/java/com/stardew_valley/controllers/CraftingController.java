package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.building.Building;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.crafting.*;
import com.stardew_valley.models.crafting.enums.CraftingDevices;
import com.stardew_valley.models.crafting.enums.CraftingRecipes;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.enums.commands.CraftingCommands;
import com.stardew_valley.models.data.Repository;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraftingController extends Controller {
    public CraftingController(Repository repo) {
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

//        switch (matchedCommand) {
//            case SHOW_RECIPE:
//                return showRecipe();
//            case CRAFT:
//                return craft(command);
//            case CHEAT_ADD_RECIPE:
//                return cheatAddRecipe(command);
//            case PLACE_ITEM:
//                return placeItem(command);
//        }
        return null;
    }

    public void craft(Label messageLabel,String itemName) {
        Set<CraftingRecipe> recipes = repo.getCurrentUser().getPlayer().getCraftingRecipes();

        CraftingRecipe targetRecipe = null;

        for (CraftingRecipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(itemName)) {
                targetRecipe = recipe;
                break;
            }
        }

        if (targetRecipe == null) {
            messageLabel.setText("Recipe not found.");
            return;
        }

        Map<String, Integer> requiredIngredients = targetRecipe.ingredients();

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();

        if (!inventory.hasCapacity()) {
            messageLabel.setText("Inventory is full.");
            return;
        }

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialName = entry.getKey();
            int requiredAmount = entry.getValue();

            Slot inventorySlot = inventory.getSlot(materialName);
            if (inventorySlot == null) {
                messageLabel.setText(materialName + "Slot not found.");
                return;
            }
            int itemCount = inventorySlot.getQuantity();

            if (itemCount < requiredAmount) {
                messageLabel.setText("You don't have enough " + materialName);
            }
        }

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialName = entry.getKey();
            int requiredAmount = entry.getValue();
            Slot inventorySlot = inventory.getSlot(materialName);
            inventorySlot.removeQuantity(requiredAmount);
        }

        player.getEnergy().consume(2);
        inventory.addItem(itemName, 1);

        //repo.getCurrentGame().getCurrentPlayer().addCraftingDevices(CraftingDevice);

        messageLabel.setText("Crafted " + targetRecipe.name() + " successfully!");
    }

    private Result cheatAddRecipe(String command) {
        String recipeName = "mas";

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

        if (player.haveCraftingRecipes(recipeToLearn)) {
            return new Result(false, "Recipe \"" + recipeToLearn.getName() + "\" is already learning.");
        }

        player.addCraftingRecipe(recipeToLearn);

        return new Result(true, "Recipe added");
    }

    private Result placeItem(String command) {
        String itemName = "ali";
        String directionStr = "mamad";

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();

        Item item = inventory.getNewItem(itemName);

        if (!(item instanceof CraftingDevice device)) {
            return new Result(false, item.getName() + " cannot be placed in farm.");
        }

        Slot slot = inventory.getSlot(itemName);

        if (slot == null) {
            return new Result(false, "You don't have \"" + itemName + "\" in your inventory.");
        }

        Direction direction = Direction.fromString(directionStr);

        if (direction == null) {
            return new Result(false, "invalid direction: " + directionStr);
        }

        Position position = player.getPosition();
        Position directionPosition = position.applyDirection(direction);

        device.setPosition(directionPosition.x(),directionPosition.y());
        device.setWorking(false);

        repo.getCurrentGame().getCurrentPlayer().getFarm().getTiles().get(directionPosition.x()).get(directionPosition.y()).setObject(device);
        repo.getCurrentGame().getCurrentPlayer().addCraftingDevices(device);

        slot.removeQuantity(1);

        return new Result(true, item.getName() + " placed successfully at (" + directionPosition.x() + ", " + directionPosition.y() + ").");
    }
}
