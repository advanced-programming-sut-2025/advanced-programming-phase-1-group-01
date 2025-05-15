package controllers.ShopControllers;

import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.shop.CarpenterShop;
import models.shop.enums.CarpenterShopProducts;
import models.shop.enums.CarpenterShopBuildings;

public class CarpenterController extends ShopController {

    public CarpenterController(Repository repo) {
        super(repo);
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

        for (CarpenterShopBuildings building : shop.getAllBuildings()) {
            int stock = shop.getBuildingProduct(building);
            info.append(building.getName())
                    .append(": ")
                    .append(building.getCost())
                    .append("g")
                    .append(building.getStoneRequired())
                    .append("stone")
                    .append(building.getWoodRequired())
                    .append("wood")
                    .append(" (")
                    .append(building.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                    .append(")")
                    .append("\n");
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

        for (CarpenterShopBuildings building : shop.getAllBuildings()) {
            int stock = shop.getBuildingProduct(building);
            if (building.getDailyLimit() == -1 || stock > 0) {
                info.append(building.getName())
                        .append(": ")
                        .append(building.getCost())
                        .append("g")
                        .append(building.getStoneRequired())
                        .append("stone")
                        .append(building.getWoodRequired())
                        .append("wood")
                        .append(" (")
                        .append(building.getDailyLimit() == -1 ? "unlimited" : stock + " left")
                        .append(")")
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

        CarpenterShop shop = repo.getCurrentGame().getCarpenterShop();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (CarpenterShopProducts product : CarpenterShopProducts.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
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
                // inventory
                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }

        for (CarpenterShopBuildings building : CarpenterShopBuildings.values()) {
            if (building.getName().equalsIgnoreCase(productName)) {
                int cost = building.getCost();
                int stock = shop.getBuildingProduct(building);
                int stone = building.getStoneRequired();
                int wood = building.getWoodRequired();

//                if (wood < ) {
//                    return new Result(false, "not enough wood for this building");
//                }
//
//                if (stone < ) {
//                    return new Result(false, "not enough stone for this building");
//                }

                if (building.getDailyLimit() != -1 && stock <= 0) {
                    return new Result(false, "building not available today");
                }

                if (player.getNumOfCoins() < cost) {
                    return new Result(false, "not enough coins");
                }

                player.setNumOfCoins(player.getNumOfCoins() - cost);
                if (building.getDailyLimit() != -1) {
                    shop.updateBuildingPurchase(building);
                }
                // build building
                return new Result(true, "built: " + building.getName());
            }
        }

        return new Result(false, "product or building not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 9 && hour <= 20;
    }
}

