package controllers.ShopControllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.dateTime.Season;
import models.shop.JojaMart;
import models.shop.enums.JojaMartProducts;

public class JojaMartController extends ShopController {

    public JojaMartController(Repository repo) {
        super(repo);
    }

    protected Result showAllProducts() {
        JojaMart shop = repo.getCurrentGame().getJojaMart();
        StringBuilder info = new StringBuilder();

        // Permanent Stock
        info.append("Permanent Stock:\n");
        for (JojaMartProducts product : shop.getAllProducts()) {
            if (product.getSeason() == Season.SPECIAL) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice())
                        .append("g (")
                        .append(product.getDailyLimit() == -1 ? "unlimited" : product.getDailyLimit() + " per day")
                        .append(")\n");
            }
        }

        // Seasonal Stocks
        for (Season season : Season.values()) {
            if (season == Season.SPECIAL) continue;

            info.append("\n").append(season.name()).append(" Stock:\n");

            for (JojaMartProducts product : shop.getAllProducts()) {
                if (product.getSeason() == season) {
                    info.append(product.getName())
                            .append(": ")
                            .append(product.getPrice())
                            .append("g (")
                            .append(product.getDailyLimit() == -1 ? "unlimited" : product.getDailyLimit() + " per day")
                            .append(")\n");
                }
            }
        }

        return new Result(true, info.toString());
    }

    protected Result showAllAvailableProducts() {
        JojaMart shop = repo.getCurrentGame().getJojaMart();
        String currentSeason = repo.getCurrentGame().getTimeManager().getNow().getSeason().toString();
        StringBuilder info = new StringBuilder();

        info.append("Permanent Stock:\n");
        for (JojaMartProducts product : shop.getAllProducts()) {
            if (!product.getSeason().name().equalsIgnoreCase("SPECIAL")) continue;

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

        info.append("\n").append(currentSeason.substring(0, 1).toUpperCase()).append(currentSeason.substring(1).toLowerCase()).append(" Stock:\n");
        for (JojaMartProducts product : shop.getAllProducts()) {
            if (!product.getSeason().name().equalsIgnoreCase(currentSeason)) continue;

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

        JojaMart shop = repo.getCurrentGame().getJojaMart();
        Player player = repo.getCurrentGame().getCurrentPlayer();
        String currentSeason = repo.getCurrentGame().getTimeManager().getNow().getSeason().toString();

        for (JojaMartProducts product : JojaMartProducts.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int totalCost = product.getPrice() * count;
                int stock = shop.getProductStock(product);

                if (!product.getSeason().name().equalsIgnoreCase( currentSeason)) {
                    return new Result(false, "You don't have enough stock to purchase");
                }

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
                //inventory
                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }
        return new Result(false, "product not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 9 && hour <= 23;
    }
}
