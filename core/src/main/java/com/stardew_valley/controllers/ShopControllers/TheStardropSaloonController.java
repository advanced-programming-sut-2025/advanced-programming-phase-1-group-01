package com.stardew_valley.controllers.ShopControllers;

import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.shop.enums.StardropSallonCommands;
import com.stardew_valley.models.shop.enums.TheStardropSaloonProducts;

public class TheStardropSaloonController extends ShopController {

    public TheStardropSaloonController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {

        StardropSallonCommands matchedCommand = null;

        for (StardropSallonCommands cmd : StardropSallonCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case SHOW_ALL_PRODUCTS:
                return showAllProducts();
            case SHOW_ALL_AVAILABLE_PRODUCTS:
                return showAllAvailableProducts();
            case STAR_DROP_SALOON:
                return purchase(command);
        }

        return null;
    }

    protected Result showAllProducts() {
        return null;
    }

    protected Result showAllAvailableProducts() {
       return null;
    }

    protected Result purchase(String command) {
        String itemName;
        String countStr;
        int count;

        if (command.contains("-n")) {
            itemName = extractValue(command, "stardrop", "-n");
            countStr = extractValue(command, "-n", null);
        }

        else {
            itemName = extractValue(command, "stardrop", null);
            countStr = "1";
        }
        count = Integer.parseInt(countStr);

//        TheStardropSaloon shop = repo.getCurrentGame().getTheStardropSaloon();
//        Player player = repo.getCurrentGame().getCurrentPlayer();
//
//        for (TheStardropSaloonProducts product : TheStardropSaloonProducts.values()) {
//            if (product.getName().equalsIgnoreCase(itemName)) {
//                int totalCost = product.getPrice() * count;
//                int stock = shop.getProductStock(product);
//
//                if (product.getDailyLimit() != -1 && stock < count) {
//                    return new Result(false, "not enough stock for this product");
//                }
//
//                if (player.getNumOfCoins() < totalCost) {
//                    return new Result(false, "not enough coins");
//                }
//
//                Inventory inventory = player.getInventory();
//                inventory.addItem(itemName, count);
//
//                player.setNumOfCoins(player.getNumOfCoins() - totalCost);
//                if (product.getDailyLimit() != -1) {
//                    shop.updateProductPurchase(product, count);
//                }

                return new Result(true, "purchased " + count + "x ");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 12 && hour <= 23 ;
    }
}

