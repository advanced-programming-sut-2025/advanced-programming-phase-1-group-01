package controllers;

import models.Item;
import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.character.player.Slot;
import models.data.Repository;
import models.enums.commands.SellCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellController extends Controller {
    SellController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        Player player = repo.getCurrentGame().getCurrentPlayer();



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
        String productName;
        String countStr;
        int count;
        if (command.contains("-n")) {
            productName = extractValue(command,"sell","-n");
            countStr = extractValue(command,"-n",null);
        }

        else {
            productName = extractValue(command,"sell",null);
            countStr = "1";
        }

        count = Integer.parseInt(countStr);

        Player player = repo.getCurrentGame().getCurrentPlayer();
        if (!player.isNearToSellBucket()) {
            return new Result(false, "You are not near a Sell Bucket!");
        }

        Item item = Inventory.getNewItem(productName);

        if (item == null) {
            return new Result(false, "product does not exist");
        }

        Inventory inventory = player.getInventory();
        Slot slot = inventory.getSlot(productName);

        if (slot == null) {
            return new Result(false, "You don't have " + productName + " in your inventory.");
        }

        if (inventory.getSlot(productName).getQuantity() < count) {
            return new Result(false, "You don't have enough " + productName + " in your inventory.");
        }

        slot.removeQuantity(count);
        //player.increaseCoins(item.getPrice());

        return new Result(true, count + " of " + productName + " have been sold!");
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
