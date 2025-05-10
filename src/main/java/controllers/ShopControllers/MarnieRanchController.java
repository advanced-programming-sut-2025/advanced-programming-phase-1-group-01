package controllers.ShopControllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.shop.MarnieRanch;
import models.shop.enums.MarnieRanchProducts;

public class MarnieRanchController extends ShopController {

    public MarnieRanchController(Repository repo) {
        super(repo);
    }

    protected Result showAllProducts() {
        MarnieRanch shop = repo.getCurrentGame().getMarnieRanch();
        StringBuilder info = new StringBuilder();

        for (MarnieRanchProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            info.append(product.getName())
                    .append(": ")
                    .append(product.getPrice())
                    .append("g (")
                    .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                    .append(")")
                    .append("required :")
                    .append(product.getBuildingRequired())
                    .append("\n");
        }

        return new Result(true, info.toString());
    }

    protected Result showAllAvailableProducts() {
        MarnieRanch shop = repo.getCurrentGame().getMarnieRanch();
        StringBuilder info = new StringBuilder();

        for (MarnieRanchProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            if (product.getDailyLimit() == -1 || stock > 0) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice())
                        .append("g (")
                        .append(product.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                        .append(")")
                        .append("required :")
                        .append(product.getBuildingRequired())
                        .append("\n");
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

        MarnieRanch shop = repo.getCurrentGame().getMarnieRanch();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (MarnieRanchProducts product : MarnieRanchProducts.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int totalCost = product.getPrice() * count;
                int stock = shop.getProductStock(product);
                String required = product.getBuildingRequired();

//                if (required.equalsIgnoreCase("")) {
//                    return new Result(false, "Required product is empty");
//                }

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
        return hour >= 9 && hour <= 16;
    }
}

