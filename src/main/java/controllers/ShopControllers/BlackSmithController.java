package controllers.ShopControllers;

import models.Item;
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
import models.tool.*;
import models.tool.enums.*;

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
            case TOOLS_UPGRADE:
                return toolUpgrade(command);
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
        return new Result(false, "product not found");
    }

    private Result toolUpgrade(String command) {
        String itemName = extractValue(command, "upgrade", null);
        Player player = repo.getCurrentGame().getCurrentPlayer();

        Slot slot = player.getInventory().getSlot(itemName);

        if (slot == null) {
            return new Result(false, "item not found in inventory");
        }

        Item item = slot.getItem();

        if (item instanceof Tool tool) {
            if (tool instanceof Axe axe) {
                if (axe.getType() == AxeType.PRIMARY) {
                    Slot requiredSlot = player.getInventory().getSlot("Copper Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Copper Bar");
                    }
                    axe.upgrade();
                    return new Result(true, "Upgraded axe to " + axe.getType());
                } else if (axe.getType() == AxeType.COPPER) {
                    Slot requiredSlot = player.getInventory().getSlot("Iron Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iron Bar");
                    }
                    axe.upgrade();
                    return new Result(true, "Upgraded axe to " + axe.getType());
                } else if (axe.getType() == AxeType.IRON) {
                    Slot requiredSlot = player.getInventory().getSlot("Gold Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Gold Bar");
                    }
                    axe.upgrade();
                    return new Result(true, "Upgraded axe to " + axe.getType());
                } else if (axe.getType() == AxeType.GOLD) {
                    Slot requiredSlot = player.getInventory().getSlot("Iridium Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iridium Bar");
                    }
                    axe.upgrade();
                    return new Result(true, "Upgraded axe to " + axe.getType());
                }
            }

            if (tool instanceof Hoe hoe) {
                if (hoe.getType() == HoeType.PRIMARY) {
                    Slot requiredSlot = player.getInventory().getSlot("Copper Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Copper Bar");
                    }

                    if (player.getNumOfCoins() < 2000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(2000);
                    hoe.upgrade();
                    return new Result(true, "Upgraded hoe to " + hoe.getType());
                } else if (hoe.getType() == HoeType.COPPER) {
                    Slot requiredSlot = player.getInventory().getSlot("Iron Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iron Bar");
                    }
                    if (player.getNumOfCoins() < 5000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(5000);
                    hoe.upgrade();
                    return new Result(true, "Upgraded hoe to " + hoe.getType());
                } else if (hoe.getType() == HoeType.IRON) {
                    Slot requiredSlot = player.getInventory().getSlot("Gold Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Gold Bar");
                    }
                    if (player.getNumOfCoins() < 10000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(10000);
                    hoe.upgrade();
                    return new Result(true, "Upgraded hoe to " + hoe.getType());
                } else if (hoe.getType() == HoeType.GOLD) {
                    Slot requiredSlot = player.getInventory().getSlot("Iridium Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iridium Bar");
                    }
                    if (player.getNumOfCoins() < 25000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(25000);
                    hoe.upgrade();
                    return new Result(true, "Upgraded hoe to " + hoe.getType());
                }
            }

            if (tool instanceof Pickaxe pickaxe) {
                if (pickaxe.getType() == PickaxeType.PRIMARY) {
                    Slot requiredSlot = player.getInventory().getSlot("Copper Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Copper Bar");
                    }
                    if (player.getNumOfCoins() < 2000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(2000);
                    pickaxe.upgrade();
                    return new Result(true, "Upgraded pickaxe to " + pickaxe.getType());
                } else if (pickaxe.getType() == PickaxeType.COPPER) {
                    Slot requiredSlot = player.getInventory().getSlot("Iron Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iron Bar");
                    }
                    if (player.getNumOfCoins() < 5000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(5000);
                    pickaxe.upgrade();
                    return new Result(true, "Upgraded pickaxe to " + pickaxe.getType());
                } else if (pickaxe.getType() == PickaxeType.IRON) {
                    Slot requiredSlot = player.getInventory().getSlot("Gold Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Gold Bar");
                    }
                    if (player.getNumOfCoins() < 10000) {
                        return new Result(false, "Not enough Coins");
                    }
                    pickaxe.upgrade();
                    return new Result(true, "Upgraded pickaxe to " + pickaxe.getType());
                } else if (pickaxe.getType() == PickaxeType.GOLD) {
                    Slot requiredSlot = player.getInventory().getSlot("Iridium Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iridium Bar");
                    }
                    if (player.getNumOfCoins() < 25000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(25000);
                    pickaxe.upgrade();
                    return new Result(true, "Upgraded pickaxe to " + pickaxe.getType());
                }
            }

            if (tool instanceof WateringCan wateringCan) {
                if (wateringCan.getType() == WateringCanType.PRIMARY) {
                    Slot requiredSlot = player.getInventory().getSlot("Copper Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Copper Bar");
                    }
                    if (player.getNumOfCoins() < 2000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(2000);
                    wateringCan.upgrade();
                    return new Result(true, "Upgraded watering can to " + wateringCan.getType());
                } else if (wateringCan.getType() == WateringCanType.COPPER) {
                    Slot requiredSlot = player.getInventory().getSlot("Iron Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iron Bar");
                    }

                    if (player.getNumOfCoins() < 5000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(5000);
                    wateringCan.upgrade();
                    return new Result(true, "Upgraded watering can to " + wateringCan.getType());
                } else if (wateringCan.getType() == WateringCanType.IRON) {
                    Slot requiredSlot = player.getInventory().getSlot("Gold Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Gold Bar");
                    }
                    if (player.getNumOfCoins() < 10000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(10000);
                    wateringCan.upgrade();
                    return new Result(true, "Upgraded watering can to " + wateringCan.getType());
                } else if (wateringCan.getType() == WateringCanType.GOLD) {
                    Slot requiredSlot = player.getInventory().getSlot("Iridium Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iridium Bar");
                    }
                    if (player.getNumOfCoins() < 25000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(25000);
                    wateringCan.upgrade();
                    return new Result(true, "Upgraded watering can to " + wateringCan.getType());
                }
            }

            if (tool instanceof TrashCan trashCan) {
                if (trashCan.getType() == TrashCanType.PRIMARY) {
                    Slot requiredSlot = player.getInventory().getSlot("Copper Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Copper Bar");
                    }

                    if (player.getNumOfCoins() < 2000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(2000);
                    trashCan.upgrade();
                    return new Result(true, "Upgraded trash can to " + trashCan.getType());
                } else if (trashCan.getType() == TrashCanType.COPPER) {
                    Slot requiredSlot = player.getInventory().getSlot("Iron Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iron Bar");
                    }
                    if (player.getNumOfCoins() < 5000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(5000);
                    trashCan.upgrade();
                    return new Result(true, "Upgraded trash can to " + trashCan.getType());
                } else if (trashCan.getType() == TrashCanType.IRON) {
                    Slot requiredSlot = player.getInventory().getSlot("Gold Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Gold Bar");
                    }
                    if (player.getNumOfCoins() < 10000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(10000);
                    trashCan.upgrade();
                    return new Result(true, "Upgraded trash can to " + trashCan.getType());
                } else if (trashCan.getType() == TrashCanType.GOLD) {
                    Slot requiredSlot = player.getInventory().getSlot("Iridium Bar");
                    if (requiredSlot == null || requiredSlot.getQuantity() < 5) {
                        return new Result(false, "Not enough Iridium Bar");
                    }
                    if (player.getNumOfCoins() < 25000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(25000);
                    trashCan.upgrade();
                    return new Result(true, "Upgraded trash can to " + trashCan.getType());
                }
            }

            if (tool instanceof Backpack backpack) {
                if (backpack.getType() == BackpackType.SMALL) {
                    if (player.getNumOfCoins() < 2000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(2000);
                    backpack.upgrade();
                }

                else if (backpack.getType() == BackpackType.BIG) {
                    if (player.getNumOfCoins() < 10000) {
                        return new Result(false, "Not enough Coins");
                    }
                    player.consumeCoins(10000);
                    backpack.upgrade();
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
