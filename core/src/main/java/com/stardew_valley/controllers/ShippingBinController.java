package com.stardew_valley.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.animal.ProductQuality;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.BanSellItem;
import com.stardew_valley.models.enums.commands.SellCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShippingBinController extends Controller {
    ShippingBinController(Repository repo) {
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

//        switch (matchedCommand) {
//            case SELL:
//                return sell(command);
//        }
        return null;
    }

    public void sell(String product, int quantity, Label message) {
        String[] parts = product.split(" ");
        String productName = parts[0];

        Player player = repo.getCurrentGame().getCurrentPlayer();
        Item item = player.getInventory().getNewItem(productName);

        Inventory inventory = player.getInventory();
        Slot slot = inventory.getSlot(productName);

        if (BanSellItem.isBanned(item.getName().toLowerCase())) {
            message.setText("You can't sell " + productName);
            return;
        }

        if (slot.getQuantity() < quantity) {
            message.setText("You don't have enough " + productName + " in your inventory.");
            return;
        }

        slot.removeQuantity(quantity);
        double finalPrice = item.getPrice() * quantity;
        int totalPrice = (int) finalPrice;
        repo.getCurrentGame().getDelayedPaymentSystem().addPendingSale(player, productName, quantity, totalPrice);

        message.setText(quantity + "x " + productName + " have been sold for " + totalPrice + " coins!");
    }
}
