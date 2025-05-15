package controllers;

import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.data.Repository;
import models.enums.commands.SellCommands;

public class SellController extends Controller {
    SellController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        Player player = repo.getCurrentGame().getCurrentPlayer();

        if (!player.isNearToSellBucket()) {
            return new Result(false, "You are not near a Sell Bucket!");
        }

        SellCommands matchedCommand = null;

        for (SellCommands cmd : SellCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case SELL:
                return sell(command);
        }
        return null;
    }

    private Result sell(String command) {
        String[] tokens = command.split(" ");
        String productName = tokens[1];

        int count;

        try {
            count = Integer.parseInt(tokens[3]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            count = 1;
        }

        Player player = repo.getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();

        //check is sellable
//        if (inventory.getSlot(productName).getItem()) {
//
//        }

        if (inventory.getSlot(productName) == null) {
            return new Result(false, "You don't have " + productName + " in your inventory.");
        }

        if (inventory.getSlot(productName).getQuantity() < count) {
            return new Result(false, "You don't have enough " + productName + " in your inventory.");
        }

        return new Result(true, count + " of " + productName + " have been sold!");
    }
}
