package controllers.ShopControllers;

import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.data.Repository;
import models.dateTime.Season;
import models.shop.Blacksmith;
import models.shop.PierreGeneralStore;
import models.shop.enums.*;

public class PierreGeneralStoreController extends ShopController {
    public PierreGeneralStoreController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();

        if (!isShopOpen(hour)) {
            return new Result(false, "shop is closed");
        }

        PierreCommands matchedCommand = null;

        for (PierreCommands cmd : PierreCommands.values()) {
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
            case PIERRE_STORE:
                return purchase(command);
            case CHEAT_COINS:
                return cheatCoins(command);
        }

        return null;
    }

    @Override
    protected Result showAllProducts() {
        StringBuilder info = new StringBuilder();

        PierreGeneralStore shop = repo.getCurrentGame().getPierreGeneralStore();
        Season season = repo.getCurrentGame().getTimeManager().getNow().getSeason();
        for (PierreGeneralStoreProducts product : shop.getAllProducts()) {
            info.append(product.getName())
                    .append(": ")
                    .append(product.getPrice(season))
                    .append("g\n");
        }

        return new Result(true, info.toString());
    }

    @Override
    protected Result showAllAvailableProducts() {
        PierreGeneralStore shop = repo.getCurrentGame().getPierreGeneralStore();
        Season season = repo.getCurrentGame().getTimeManager().getNow().getSeason();
        StringBuilder info = new StringBuilder();

        for (PierreGeneralStoreProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            if (product.getDailyLimit() == -1 || stock > 0) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice(season))
                        .append("g")
                        .append(" (")
                        .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                        .append(")\n");
            }
        }
        return new Result(true, info.toString());
    }

    @Override
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

        PierreGeneralStore shop = repo.getCurrentGame().getPierreGeneralStore();
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Season season = repo.getCurrentGame().getTimeManager().getNow().getSeason();

        for (PierreGeneralStoreProducts product : PierreGeneralStoreProducts.values()) {
            if (product.getName().equalsIgnoreCase(itemName)) {
                int totalCost = product.getPrice(season) * count;
                int stock = shop.getProductStock(product);

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
                inventory.addItem(itemName,count);

                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }
        return new Result(false, "product not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 9 && hour <= 17;
    }
}
