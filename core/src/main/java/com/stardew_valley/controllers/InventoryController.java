package com.stardew_valley.controllers;

import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.commands.InventoryCommands;
import com.stardew_valley.models.tool.TrashCan;
import com.stardew_valley.models.Item;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryController extends Controller {
    InventoryController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        InventoryCommands matchedCommand = null;

        for (InventoryCommands command : InventoryCommands.values()) {
            if (commandLine.matches(command.getRegex())) {
                matchedCommand = command;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        String itemName;
        switch (matchedCommand) {
            case INVENTORY_SHOW:
                return inventoryShow();
            case INVENTORY_TRASH_1:
                itemName = commandLine.substring(commandLine.indexOf("-i") + 2, commandLine.indexOf("-n") - 1).trim();
                int quantity;
                try {
                    quantity = Integer.parseInt(commandLine.substring(commandLine.indexOf("-n") + 2));
                } catch (NumberFormatException e) {
                    return new Result(false, "invalid quantity");
                }
                return inventoryTrash(itemName, quantity);
            case INVENTORY_TRASH_2:
                itemName = commandLine.substring(commandLine.indexOf("-i") + 2).trim();
                return inventoryTrash(itemName);
            case CHEAT_ADD_ITEM:
                return cheatAddItem(commandLine);
            case CHEAT_COINS:
                return cheatCoins(commandLine);
            case SHOW_COINS:
                return showCoin();

        }
        return new Result(false, "invalid command");
    }

    private Result inventoryShow() {
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();

        StringBuilder resultMsg = new StringBuilder();

        for (Slot slot : inventory.getSlots()) {
            if (slot == null || slot.getItem() == null) continue;
            resultMsg.append(slot);
            if (inventory.getSlots().indexOf(slot) != inventory.getSlots().size() - 1) {
                resultMsg.append("\n");
            }
        }

        return new Result(true, resultMsg.toString());
    }

    private Result inventoryTrash(String itemName, int quantity) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();
        Slot slot = inventory.getSlot(itemName);

        if (slot == null) {
            return new Result(false, "you don't have this item at all");
        } else if (slot.getQuantity() < quantity) {
            return new Result(false, "you don't have this much quantity");
        }

        slot.removeQuantity(quantity);

        TrashCan trashCan = (TrashCan) inventory.getSlot("trash can").getItem();
        for (int i = 0; i < quantity; i++) {
            player.increaseCoins(trashCan.getReturnValue(slot.getItem().getPrice()));
        }

        if (inventory.getSlots().contains(slot)) {
            return new Result(true, "%d of %s has been trashed successfully".formatted(quantity, itemName));
        } else {
            return new Result(true, "%s has been trashed successfully".formatted(itemName));
        }
    }

    private Result inventoryTrash(String itemName) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();
        Slot slot = inventory.getSlot(itemName);

        if (slot == null) {
            return new Result(false, "you don't have this item at all");
        }

        inventory.removeSlot(slot);

        Slot trashCanSlot = inventory.getSlot("trash can");
        if (trashCanSlot != null) {
            TrashCan trashCan = (TrashCan) inventory.getSlot("trash can").getItem();
            for (int i = 0; i < slot.getQuantity(); i++) {
                player.increaseCoins(trashCan.getReturnValue(slot.getItem().getPrice()));
            }
        }
        return new Result(true, "%s has been trashed successfully".formatted(itemName));
    }

    private Result cheatCoins(String command) {
        String countStr = extractValue(command, "add", "dollars");
        int count = Integer.parseInt(countStr);
        repo.getCurrentGame().getCurrentPlayer().increaseCoins(count);
        return new Result(true, "coins have been added to your balance: " + count);
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
        }

        if (!player.getInventory().hasCapacity()) {
            return new Result(false, "inventory is full");
        }

        inventory.addItem(itemName, itemCount);
        return new Result(true, "Added x" + itemCount + " " + itemName + " to inventory.");
    }

    private Result showCoin() {
        int coins = repo.getCurrentGame().getCurrentPlayer().getNumOfCoins();
        return new Result(true, "You have " + coins + " coins.");
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
