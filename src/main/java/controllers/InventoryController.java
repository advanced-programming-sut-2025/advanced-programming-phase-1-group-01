package controllers;

import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.character.player.Slot;
import models.data.Repository;
import models.enums.commands.InventoryCommands;
import models.tool.TrashCan;
import models.weather.Weather;

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
        }
        return new Result(false, "invalid command");
    }

    private Result inventoryShow() {
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();

        StringBuilder resultMsg = new StringBuilder();

        for (Slot slot : inventory.getSlots()) {
            resultMsg.append(slot.toString());
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
}
