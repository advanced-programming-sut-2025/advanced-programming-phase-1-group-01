package controllers.ShopControllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.shop.Blacksmith;
import models.shop.enums.BlacksmithProducts;
import models.shop.enums.BlacksmithUpgrade;

public class BlackSmithController extends ShopController {

    public BlackSmithController(Repository repo) {
        super(repo);
    }

    @Override
    protected Result showAllProducts() {
        StringBuilder info = new StringBuilder();

        Blacksmith shop = repo.getCurrentGame().getBlacksmith();

        for (BlacksmithProducts product : shop.getAllProducts()) {
            info.append(product.getName())
                    .append(": ")
                    .append(product.getPrice())
                    .append("g\n");
        }

        for (BlacksmithUpgrade upgrade : shop.getAllUpgrades()) {
            info.append(upgrade.getName())
                    .append(": ")
                    .append(upgrade.getPrice())
                    .append("g\n");
        }

        return new Result(true, info.toString());
    }

    @Override
    protected Result showAllAvailableProducts() {
            Blacksmith shop = repo.getCurrentGame().getBlacksmith();
            StringBuilder info = new StringBuilder();

            for (BlacksmithProducts product : shop.getAllProducts()) {
                int stock = shop.getProductStock(product);
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

            for (BlacksmithUpgrade upgrade : shop.getAllUpgrades()) {
                int stock = shop.getUpgradeStock(upgrade);
                if (upgrade.getDailyLimit() == -1 || stock > 0) {
                    info.append(upgrade.getName())
                            .append(": ")
                            .append(upgrade.getPrice())
                            .append("g")
                            .append(" (")
                            .append(upgrade.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                            .append(")\n");
                }
            }
            return new Result(true, info.toString());
    }

    @Override
    protected Result purchase(String command) {
        String[] tokens = command.split(" ");

        String productName = tokens[1];
        int count;

        try {
            count = Integer.parseInt(tokens[3]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            count = 1;
        }

        Blacksmith shop = repo.getCurrentGame().getBlacksmith();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (BlacksmithProducts product : BlacksmithProducts.values()) {
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
                //inventory
                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }

        for (BlacksmithUpgrade upgrade : BlacksmithUpgrade.values()) {
            if (upgrade.getName().equalsIgnoreCase(productName)) {
                int cost = upgrade.getPrice();
                int stock = shop.getUpgradeStock(upgrade);

                if (upgrade.getDailyLimit() != -1 && stock <= 0) {
                    return new Result(false, "upgrade not available today");
                }

                if (player.getNumOfCoins() < cost) {
                    return new Result(false, "not enough coins");
                }

                player.setNumOfCoins(player.getNumOfCoins() - cost);
                if (upgrade.getDailyLimit() != -1) {
                    shop.updateUpgradePurchase(upgrade);
                }
                //upgrade tool
                return new Result(true, "upgraded: " + upgrade.getName());
            }
        }

        return new Result(false, "product not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour <= 16 && hour >= 9;
    }
}
