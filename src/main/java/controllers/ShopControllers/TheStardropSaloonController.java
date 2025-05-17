package controllers.ShopControllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.shop.TheStardropSaloon;
import models.shop.enums.StardropSallonCommands;
import models.shop.enums.TheStardropSaloonProducts;

public class TheStardropSaloonController extends ShopController {

    public TheStardropSaloonController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();

        if (!isShopOpen(hour)) {
            return new Result(false, "shop is closed");
        }

        StardropSallonCommands matchedCommand = null;

        for (StardropSallonCommands cmd : StardropSallonCommands.values()) {
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
            case STAR_DROP_SALOON:
                return purchase(command);
            case CHEAT_COINS:
                return cheatCoins(command);
        }

        return null;
    }

    protected Result showAllProducts() {
        StringBuilder info = new StringBuilder();
        TheStardropSaloon shop = repo.getCurrentGame().getTheStardropSaloon();

        for (TheStardropSaloonProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            info.append(product.getName())
                    .append(": ")
                    .append(product.getPrice())
                    .append("g (")
                    .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                    .append(")\n");
        }

        return new Result(true, info.toString());
    }

    protected Result showAllAvailableProducts() {
        StringBuilder info = new StringBuilder();
        TheStardropSaloon shop = repo.getCurrentGame().getTheStardropSaloon();

        for (TheStardropSaloonProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            if (product.getDailyLimit() == -1 || stock > 0) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice())
                        .append("g (")
                        .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                        .append(")\n");
            }
        }

        return new Result(true, info.toString());
    }

    protected Result purchase(String command) {
        String[] tokens = command.split(" ");
        String productName = tokens[1];
        int count;

        try {
            count = Integer.parseInt(tokens[3]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            count = 1;
        }

        TheStardropSaloon shop = repo.getCurrentGame().getTheStardropSaloon();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (TheStardropSaloonProducts product : TheStardropSaloonProducts.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int totalCost = product.getPrice() * count;
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

                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }
        return new Result(false, "product not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 12 && hour <= 23 ;
    }
}

