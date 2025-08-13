package com.stardew_valley.controllers.ShopControllers;

import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.shop.MarnieRanch;
import com.stardew_valley.models.shop.enums.MarnieCommands;
import com.stardew_valley.models.shop.enums.MarnieRanchProducts;

public class MarnieRanchController extends ShopController {

    public MarnieRanchController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {

        MarnieCommands matchedCommand = null;

        for (MarnieCommands cmd : MarnieCommands.values()) {
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
            case MARNIE_RANCH:
                return purchase(command);
        }

        return null;
    }

    protected Result showAllProducts() {
        MarnieRanch shop = repo.getCurrentGame().getMarnieRanch();
        StringBuilder info = new StringBuilder();

        info.append("show all products\n");

        for (MarnieRanchProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            info.append(product.getName())
                    .append(": ")
                    .append(product.getPrice())
                    .append("g (")
                    .append(")")
                    .append("\n");
        }

        return new Result(true, info.toString());
    }

    protected Result showAllAvailableProducts() {
        MarnieRanch shop = repo.getCurrentGame().getMarnieRanch();
        StringBuilder info = new StringBuilder();

        info.append("Available products:\n");

        for (MarnieRanchProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            if (product.getDailyLimit() == -1 || stock > 0) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice())
                        .append("g (")
                        .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                        .append(")")
                        .append("\n");
            }
        }
        return new Result(true, info.toString());
    }

    protected Result purchase(String command) {
        String itemName;
        String countStr;
        int count;

        if (command.contains("-n")) {
            itemName = extractValue(command, "marnie", "-n");
            countStr = extractValue(command, "-n", null);
        }

        else {
            itemName = extractValue(command, "marnie", null);
            countStr = "1";
        }
        count = Integer.parseInt(countStr);

        MarnieRanch shop = repo.getCurrentGame().getMarnieRanch();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (MarnieRanchProducts product : MarnieRanchProducts.values()) {
            if (product.getName().equalsIgnoreCase(itemName)) {
                int totalCost = product.getPrice() * count;
                int stock = shop.getProductStock(product);


                if (product.getDailyLimit() != -1 && stock < count) {
                    return new Result(false, "not enough stock for this product");
                }

                if (player.getNumOfCoins() < totalCost) {
                    return new Result(false, "not enough coins");
                }

                Inventory inventory = player.getInventory();
                inventory.addItem(itemName,count);

                player.setNumOfCoins(player.getNumOfCoins() - totalCost);
                if (product.getDailyLimit() != -1) {
                    shop.updateProductPurchase(product, count);
                }

                return new Result(true, "purchased " + count + "x " + product.getName());
            }
        }
        return new Result(false, "product not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 9 && hour <= 16;
    }
}

