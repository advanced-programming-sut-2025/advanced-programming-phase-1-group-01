package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.building.Building;
import com.stardew_valley.models.character.player.*;
import com.stardew_valley.models.cooking.*;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.FridgeOnlyItem;
import com.stardew_valley.models.enums.commands.CookingCommands;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookingController extends Controller {
    public CookingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {

        Player player = repo.getCurrentUser().getPlayer();
        Building cottage = player.getFarm().getCottage();
        if (!player.isPlayerNearBuilding(cottage)) {
            return new Result(false, "You are not near Cottage");
        }

        CookingCommands matchedCommand = null;

        for (CookingCommands cmd : CookingCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if(matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case CHEAT_ADD_RECIPE:
                return cheatAddRecipe(command);
//            case SHOW_RECIPE:
//                return showRecipe();
//            case PUT_REFRIGERATOR:
//                return putRefrigerator(command);
//            case PICK_REFRIGERATOR:
//                return pickRefrigerator(command);
//            case COOKING_PREPARE:
//                return cookingPrepare(command);
//            case EAT:
//                return eat(command);
        }
        return null;
    }

    private Result cheatAddRecipe(String command) {
        String recipeName = extractValue(command,"-r",null);

        Player player = repo.getCurrentUser().getPlayer();

        CookingRecipes matched = null;

        for (CookingRecipes recipeEnum : CookingRecipes.values()) {
            if (recipeEnum.name().equalsIgnoreCase(recipeName)) {
                matched = recipeEnum;
                break;
            }
        }

        if (matched == null) {
            return new Result(false, "Recipe \"" + recipeName + "\" does not exist.");
        }


        CookingRecipe recipeToLearn = matched.toRecipe();

        if (player.haveCookingRecipe(recipeToLearn)) {
            return new Result(false, "Recipe \"" + recipeName + "\" already exists.");
        }

        player.addCookingRecipe(recipeToLearn);


        return new Result(true, "Recipe added");
    }

    public void put(Label messageLabel, String itemStr, String count) {
        int itemCount;

        try {
            itemCount = Integer.parseInt(count);
        }
        catch (NumberFormatException e) {
            messageLabel.setText("please enter an integer");
            return;
        }

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();

        if (inventory.getSlot(itemStr) == null) {
            messageLabel.setText("You do not have this " + itemStr + " in your inventory.");
            return;
        }

        if (!FridgeOnlyItem.isFridgeItem(itemStr)) {
             messageLabel.setText("You cannot place non-food items in the fridge.");
             return;
        }

        if (!player.getRefrigerator().refrigerateHasCapacity()) {
            messageLabel.setText("You do not have refrigerate capacity.");
            return;
        }

        Item item = inventory.getSlot(itemStr).getItem();
        Slot slot = inventory.getSlot(itemStr);

        slot.removeQuantity(itemCount);
        player.getRefrigerator().addItem(item,itemCount);
        messageLabel.setText("Item added to refrigerator");
    }

    public void pick(Label messageLabel, String itemStr, String count) {
        int itemCount;

        try {
            itemCount = Integer.parseInt(count);
        }
        catch (NumberFormatException e) {
            messageLabel.setText("please enter an integer");
            return;
        }

        Player player = repo.getCurrentUser().getPlayer();
        Refrigerator refrigerator = player.getRefrigerator();
        Item item = player.getInventory().getNewItem(itemStr);

        if (refrigerator.containsItem(item)) {
            messageLabel.setText("You do not have this " + itemStr + " in your refrigerator.");
            return;
        }

        if (refrigerator.containsItem(item, itemCount)) {
            messageLabel.setText("You do not have enough of this " + itemStr + " in your refrigerator.");
            return;
        }

        Inventory inventory = player.getInventory();
        if (!inventory.hasCapacity()) {
            messageLabel.setText("You do not have inventory capacity.");
            return;
        }

        inventory.addItem(itemStr,itemCount);
        refrigerator.removeItem(item,itemCount);
        messageLabel.setText("Item added to inventory");
    }

    public void cook(Label messageLabel, String itemName) {

        Set<CookingRecipe> recipes = repo.getCurrentUser().getPlayer().getCookingRecipes();
        CookingRecipe targetRecipe = null;

        for (CookingRecipe recipe : recipes) {
            if (recipe.name().equalsIgnoreCase(itemName)) {
                targetRecipe = recipe;
                break;
            }
        }

        if (targetRecipe == null) {
            messageLabel.setText("Recipe not found.");
            return;
        }

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Refrigerator refrigerator = player.getRefrigerator();

        if (!inventory.hasCapacity()) {
            messageLabel.setText("You do not have enough capacity.");
            return;
        }

        Map<String, Integer> requiredIngredients = targetRecipe.ingredients();

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialName = entry.getKey();
            int requiredAmount = entry.getValue();

            int fridgeAmount = refrigerator.getQuantity(itemName);
            Slot slot = inventory.getSlot(materialName);

            if (slot == null) {
                messageLabel.setText(materialName + " slot not found.");
                return;
            }

            int inventoryAmount = slot.getQuantity();

            if (fridgeAmount + inventoryAmount < requiredAmount) {
                messageLabel.setText("You do not have enough" + materialName + " in your inventory and refrigerator.");
                return;
            }
        }

        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialNameStr = entry.getKey();
            int requiredAmount = entry.getValue();

            Item item = inventory.getSlot(materialNameStr).getItem();
            Slot slot = inventory.getSlot(materialNameStr);

            int fridgeAmount = refrigerator.getQuantity(materialNameStr);
            int remaining = requiredAmount;

            if (fridgeAmount >= remaining) {
                refrigerator.removeItem(item, remaining);
            }

            else {
                refrigerator.removeItem(item, fridgeAmount);
                remaining -= fridgeAmount;
                slot.removeQuantity(remaining);
            }
        }

        inventory.addItem(itemName,1);
        player.getEnergy().consume(3);
        messageLabel.setText(itemName + " added to your inventory");
    }

    private Result eat(String command) {
        String foodName ="ali";

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Slot slot = inventory.getSlot(foodName);

        Item item = inventory.getSlot(foodName).getItem();

        if (item == null) {
            return new Result(false, foodName + " not found.");
        }

        if (slot == null) {
            return new Result(false, "you don't have this " + foodName + " in your inventory.");
        }

        if (slot.getQuantity() == 0) {
            return new Result(false, "You do not have" + foodName + " in your inventory.");
        }

        FoodsEnum matched = null;

        for (FoodsEnum recipeEnum : FoodsEnum.values()) {
            if (recipeEnum.getName().equalsIgnoreCase(foodName)) {
                matched = recipeEnum;
                break;
            }
        }

        slot.removeQuantity(1);
        player.getEnergy().increase(matched.toFood().getEnergy());

        String buff = matched.toFood().getBuff();
        if (buff != null) {

            if (buff.equalsIgnoreCase("Max Energy")) {
                player.isBuffActivated();
            }

            else {
                AbilityType abilityType = AbilityType.valueOf(buff.toUpperCase());
                player.abilityBuff(abilityType);
            }
        }


        return new Result(true, "This " + foodName + " has been eaten.");
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
