package controllers;

import models.Item;
import models.Result;
import models.animal.ProductQuality;
import models.character.player.Inventory;
import models.character.player.Player;
import models.character.player.Slot;
import models.data.Repository;
import models.enums.BanSellItem;
import models.enums.commands.SellCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellController extends Controller {
    SellController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
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
        if (!player.isNearToSellBucket(player.getPosition().x(), player.getPosition().y())) {
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

        if (BanSellItem.isBanned(item.getName())) {
            return new Result(false, "You can't sell " + productName + " because it is already banned.");
        }

        slot.removeQuantity(count);
        ProductQuality quality = ProductQuality.getRandomProductQuality();
        double finalPrice = item.getPrice() * quality.getPriceCoefficient();
        //player.increaseCoins((int)finalPrice);

        return new Result(true, count + " x " + quality + " " + productName + " have been sold for " + finalPrice + " coins!");
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
