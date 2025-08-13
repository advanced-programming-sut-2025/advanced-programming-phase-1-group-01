package com.stardew_valley.controllers.ShopControllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.shop.JojaMart;
import com.stardew_valley.models.shop.enums.JojaMartCommands;
import com.stardew_valley.models.shop.enums.JojaMartProducts;

public class JojaMartController extends ShopController {

    public JojaMartController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {

        JojaMartCommands matchedCommand = null;

        for (JojaMartCommands cmd : JojaMartCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

//        switch (matchedCommand) {
//            case SHOW_ALL_PRODUCTS:
//                return showAllProducts();
//            case SHOW_ALL_AVAILABLE_PRODUCTS:
//                return showAllAvailableProducts();
//            case JOJA_MART:
//                return purchase(command);
//        }

        return null;
    }


    @Override
    protected Result showAllProducts() {
        return null;
    }

    protected Result purchase(String command) {
        return null;
    }

    public void buy(String itemName, int quantity, Label messageLabel) {

        JojaMart shop = repo.getCurrentGame().getJojaMart();
        Player player = repo.getCurrentGame().getCurrentPlayer();
        String[] parts = itemName.split(" ");

        for (JojaMartProducts product : JojaMartProducts.values()) {
            int totalCost = product.getPrice() * quantity;
            int stock = shop.getProductStock(product);
            if (product.getName().equals(itemName)) {

                if (product.getDailyLimit() != -1 && stock < quantity) {
                    messageLabel.setText("not enough stock for this product");
                    return;
                }

                if (player.getNumOfCoins() < totalCost) {
                    messageLabel.setText("not enough coins");
                    return;
                }

                player.setNumOfCoins(player.getNumOfCoins() - totalCost);
                if (product.getDailyLimit() != -1) {
                    shop.updateProductPurchase(product, quantity);
                }
                Inventory inventory = player.getInventory();
                inventory.addItem(itemName, quantity);
                messageLabel.setText("purchased " + quantity + "x " + product.getName());
            }
        }
    }

    @Override
    protected Result showAllAvailableProducts() {
        return null;
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour >= 9 && hour <= 23;
    }
}
