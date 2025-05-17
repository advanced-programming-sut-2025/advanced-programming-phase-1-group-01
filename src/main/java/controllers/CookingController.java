package controllers;

import models.Item;
import models.Result;
import models.building.Building;
import models.character.player.*;
import models.cooking.*;
import models.data.Repository;
import models.enums.FridgeOnlyItem;
import models.enums.commands.CookingCommands;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookingController extends Controller {
    CookingController(Repository repo) {
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
            case SHOW_RECIPE:
                return showRecipe();
            case CHEAT_ADD_RECIPE:
                return cheatAddRecipe(command);
            case PUT_REFRIGERATOR:
                return putRefrigerator(command);
            case PICK_REFRIGERATOR:
                return pickRefrigerator(command);
            case COOKING_PREPARE:
                return cookingPrepare(command);
            case EAT:
                return eat(command);
        }
        return null;
    }

    private Result showRecipe() {
        StringBuilder info = new StringBuilder("Available Recipes:\n");

        for (CookingRecipe recipe : repo.getCurrentUser().getPlayer().getCookingRecipes()) {
            info.append("- ").append(recipe.name()).append("\n");
        }

        return new Result(true,info.toString());
    }

    private Result cheatAddRecipe(String command) {
        String[] tokens = command.split(" ");
        String recipeName = tokens[4];

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
        player.addCookingRecipe(recipeToLearn);

        return new Result(true, "Recipe added");
    }

    private Result putRefrigerator(String command) {
        String[] tokens = command.split(" ");
        String itemStr = extractValue(command,"put","-a");
        String itemCountStr = extractValue(command,"-a",null);
        int itemCount = Integer.parseInt(itemCountStr);

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();

        if (inventory.getSlot(itemStr) == null) {
            return new Result(false, "You do not have this " + itemStr + " in your inventory.");
        }

        if (!FridgeOnlyItem.isFridgeItem(itemStr)) {
            return new Result(false, "You cannot place non-food items in the fridge.");
        }

        Item item = inventory.getSlot(itemStr).getItem();
        Slot slot = inventory.getSlot(itemStr);

        slot.removeQuantity(itemCount);
        player.getRefrigerator().addItem(item,itemCount);
        return new Result(true, "Item added to refrigerator");
    }

    private Result pickRefrigerator(String command) {
        String itemStr = extractValue(command,"pick","-a");
        String itemCountStr = extractValue(command,"-a",null);
        int itemCount = Integer.parseInt(itemCountStr);

        Player player = repo.getCurrentUser().getPlayer();
        Refrigerator refrigerator = player.getRefrigerator();
        Item item = player.getInventory().getNewItem(itemStr);

        if (refrigerator.containsItem(item)) {
            return new Result(false, "You do not have this " + itemStr + " in your refrigerator.");
        }

        if (refrigerator.containsItem(item, itemCount)) {
            return new Result(false, "You do not have enough of this " + itemStr + " in your refrigerator.");
        }

        Inventory inventory = player.getInventory();

        inventory.addItem(itemStr,itemCount);
        refrigerator.removeItem(item,itemCount);
        return new Result(true, "Item added to inventory");
    }

    private Result cookingPrepare(String command) {
        String itemName = extractValue(command,"prepare",null);

        Set<CookingRecipe> recipes = repo.getCurrentUser().getPlayer().getCookingRecipes();
        CookingRecipe targetRecipe = null;

        for (CookingRecipe recipe : recipes) {
            if (recipe.name().equalsIgnoreCase(itemName)) {
                targetRecipe = recipe;
                break;
            }
        }

        if (targetRecipe == null) {
            return new Result(false, "Recipe not found.");
        }

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Refrigerator refrigerator = player.getRefrigerator();

        if (!inventory.hasCapacity()) {
            return new Result(false, "You do not have enough capacity.");
        }

        Map<String, Integer> requiredIngredients = targetRecipe.ingredients();


        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String materialName = entry.getKey();
            int requiredAmount = entry.getValue();

            int fridgeAmount = refrigerator.getQuantity(itemName);
            Slot slot = inventory.getSlot(materialName);
            int inventoryAmount = slot.getQuantity();

            if (fridgeAmount + inventoryAmount < requiredAmount) {
                return new Result(false, "You do not have enough" + materialName + " in your inventory and refrigerator.");
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
        if (player.getEnergy().getAmount() == 0) {
            player.getEnergy().setHasPassedOut(true);
            repo.getCurrentGame().nextTurn();
        }

        return new Result(true, "Item added to your inventory");
    }

    private Result eat(String command) {
        String foodName = extractValue(command,"eat",null);

        Player player = repo.getCurrentUser().getPlayer();
        Inventory inventory = player.getInventory();
        Slot slot = inventory.getSlot(foodName);

        Item item = inventory.getSlot(foodName).getItem();

        if (item == null) {
            return new Result(false, foodName + " not found.");
        }

        if (slot == null) {
            return new Result(false, foodName + " not found.");
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
                player.energyBuff(matched.toFood().buffTime());
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
