package controllers.ShopControllers;

import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.data.Repository;
import models.shop.CarpenterShop;
import models.shop.enums.BlackSmithCommands;
import models.shop.enums.CarpenterCommands;
import models.shop.enums.CarpenterShopProducts;
import models.shop.enums.CarpenterShopBuildings;

public class CarpenterController extends ShopController {

    public CarpenterController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();

        if (!isShopOpen(hour)) {
            return new Result(false, "shop is closed");
        }

        CarpenterCommands matchedCommand = null;

        for (CarpenterCommands cmd : CarpenterCommands.values()) {
            if (cmd.name().equals(command)) {
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
            case CARPENTER:
                return purchase(command);
            case CHEAT_COINS:
                return cheatCoins(command);
        }

        return null;
    }

    protected Result showAllProducts() {
        StringBuilder info = new StringBuilder();

        CarpenterShop shop = repo.getCurrentGame().getCarpenterShop();

        for (CarpenterShopProducts product : shop.getAllProducts()) {
            int stock = shop.getProductAmount(product);
            info.append(product.getName())
                    .append(": ")
                    .append(product.getPrice())
                    .append("g")
                    .append(" (")
                    .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                    .append(")\n");
        }
        return new Result(true, info.toString());
    }

    protected Result showAllAvailableProducts() {
        CarpenterShop shop = repo.getCurrentGame().getCarpenterShop();
        StringBuilder info = new StringBuilder();

        for (CarpenterShopProducts product : shop.getAllProducts()) {
            int stock = shop.getProductAmount(product);
            if (product.getDailyLimit() == -1 || stock > 0) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice())
                        .append("g")
                        .append(" (")
                        .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                        .append(")\n");
            }
        }
        return new Result(true, info.toString());
    }

    protected Result purchase(String command) {
        String itemName;
        String countStr;
        int count;

        if (command.contains("-n")) {
            itemName = extractValue(command, "purchase", "-n");
            countStr = extractValue(command, "-n", null);
        }

        else {
            itemName = extractValue(command, "purchase", null);
            countStr = "1";
        }
        count = Integer.parseInt(countStr);


        CarpenterShop shop = repo.getCurrentGame().getCarpenterShop();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (CarpenterShopProducts product : CarpenterShopProducts.values()) {
            if (product.getName().equalsIgnoreCase(itemName)) {
                int totalCost = product.getPrice() * count;
                int stock = shop.getProductAmount(product);

                if (product.getDailyLimit() != -1 && stock < count) {
                    return new Result(false, "not enough stock for this product");
                }

                if (player.getNumOfCoins() < totalCost) {
                    return new Result(false, "not enough coins");
                }

                player.setNumOfCoins(player.getNumOfCoins() - totalCost);
                if (product.getDailyLimit() != -1) {
                    shop.updateProductPurchase(product, count);
                }
                Inventory inventory = player.getInventory();
                inventory.addItem(itemName, count);
                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }

        return new Result(false, "product or building not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 9 && hour <= 20;
    }
}

