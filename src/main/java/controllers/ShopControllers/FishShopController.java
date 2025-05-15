package controllers.ShopControllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.shop.FishShop;
import models.shop.enums.FishShopProducts;

public class FishShopController extends ShopController {

    public FishShopController(Repository repo) {
        super(repo);
    }

    protected Result showAllProducts() {
        StringBuilder info = new StringBuilder();

        FishShop shop = repo.getCurrentGame().getFishShop();

        for (FishShopProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
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
        FishShop shop = repo.getCurrentGame().getFishShop();
        StringBuilder info = new StringBuilder();

        for (FishShopProducts product : shop.getAllProducts()) {
            int stock = shop.getProductStock(product);
            int dailyLimit = product.getDailyLimit();

            if (product.getDailyLimit() == -1 || stock > 0) {
                info.append(product.getName())
                        .append(": ")
                        .append(product.getPrice())
                        .append("g")
                        .append(" (")
                        .append(dailyLimit == -1 ? "unlimited" : stock + " left")
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

        FishShop shop = repo.getCurrentGame().getFishShop();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (FishShopProducts product : FishShopProducts.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int totalCost = product.getPrice() * count;
                int stock = shop.getProductStock(product);
                int fishingSkillRequired = product.getFishingSkillRequired();
                int dailyLimit = product.getDailyLimit();

//                if (fishingSkillRequired != -1 && player.getFishingSkill() < fishingSkillRequired) {
//                    return new Result(false, "you need more fishing skill to purchase this");
//                }

                if (dailyLimit != -1 && stock < count) {
                    return new Result(false, "not enough stock for this product");
                }

                if (player.getNumOfCoins() < totalCost) {
                    return new Result(false, "not enough coins");
                }

                player.setNumOfCoins(player.getNumOfCoins() - totalCost);
                if (dailyLimit != -1) {
                    shop.updateProductPurchase(product);
                }
                // inventory
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


