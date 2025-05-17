package controllers.ShopControllers;

import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.character.player.Slot;
import models.data.Repository;
import models.shop.Blacksmith;
import models.shop.enums.BlackSmithCommands;
import models.shop.enums.BlacksmithProducts;
import models.shop.enums.BlacksmithUpgrade;
import models.shop.enums.ShopCommands;
import models.tool.Tool;

public class BlackSmithController extends ShopController {

    public BlackSmithController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();

        if (!isShopOpen(hour)) {
            return new Result(false, "shop is closed");
        }

        BlackSmithCommands matchedCommand = null;

        for (BlackSmithCommands cmd : BlackSmithCommands.values()) {
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
            case BLACKSMITH:
                return purchase(command);
            case CHEAT_COINS:
                return cheatCoins(command);
        }

        return null;
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

        Blacksmith shop = repo.getCurrentGame().getBlacksmith();
        Player player = repo.getCurrentGame().getCurrentPlayer();

        for (BlacksmithProducts product : BlacksmithProducts.values()) {
            if (product.getName().equalsIgnoreCase(itemName)) {
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

                Inventory inventory = player.getInventory();
                inventory.addItem(itemName,count);

                return new Result(true, "purchased " + count + " x " + product.getName());
            }
        }

        for (BlacksmithUpgrade upgrade : BlacksmithUpgrade.values()) {
            if (upgrade.getName().equalsIgnoreCase(itemName)) {
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

                Slot slot = player.getInventory().getSlot(upgrade.getRequiredItem());
                if (slot == null || slot.getQuantity() < upgrade.getRequiredAmount()) {
                    return new Result(false, "not enough \" + " + upgrade.getRequiredItem());
                }

                if (upgrade.getName().toLowerCase().contains("trash")) {
                    switch (itemName) {
                        case "Copper Trash Can" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Steel Trash Can", 1);
                        }
                        case "Steel Trash Can" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Gold Trash Can", 1);
                        }
                        case "Gold Trash Can" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Iridium Trash Can", 1);
                        }
                        default -> {
                            return new Result(false, "Invalid trash can upgrade.");
                        }
                    }
                }

                else {

                    switch (itemName) {
                        case "Copper Pickaxe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Steel Pickaxe", 1);
                        }
                        case "Steel Pickaxe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Gold Pickaxe", 1);
                        }
                        case "Gold Pickaxe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Iridium Pickaxe", 1);
                        }
                        case "Copper Axe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Steel Axe", 1);
                        }
                        case "Steel Axe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Gold Axe", 1);
                        }
                        case "Gold Axe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Iridium Axe", 1);
                        }
                        case "Copper Hoe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Steel Hoe", 1);
                        }
                        case "Steel Hoe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Gold Hoe", 1);
                        }
                        case "Gold Hoe" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Iridium Hoe", 1);
                        }
                        case "Copper Watering Can" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Steel Watering Can", 1);
                        }
                        case "Steel Watering Can" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Gold Watering Can", 1);
                        }
                        case "Gold Watering Can" -> {
                            player.getInventory().getSlot(itemName).removeQuantity(1);
                            player.getInventory().addItem("Iridium Watering Can", 1);
                        }
                        default -> {
                            return new Result(false, "Invalid tool upgrade.");
                        }
                    }
                    return new Result(true, "upgraded: " + upgrade.getName());
                }
            }
        }
        return new Result(false, "product not found");
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour <= 16 && hour >= 9;
    }
}
