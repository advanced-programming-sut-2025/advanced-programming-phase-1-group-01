package com.stardew_valley.controllers.ShopControllers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.tool.*;

public class BlackSmithController extends ShopController {

    public BlackSmithController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        return null;
    }


    @Override
    protected Result showAllProducts() {
       return null;
    }

    @Override
    protected Result showAllAvailableProducts() {
        return null;
    }

    @Override
    protected Result purchase(String command) {
      return null;
    }

    @Override
    protected boolean isShopOpen(int hour) {
        return hour <= 16 && hour >= 9;
    }

    private int getUpgradePrice(Object item) {
        if (item instanceof Hoe hoe) {
            return switch (hoe.getType()) {
                case COPPER -> 5000;
                case IRON -> 10000;
                case GOLD -> 25000;
                default -> 2000;
            };
        }
        if (item instanceof Pickaxe pickaxe) {
            return switch (pickaxe.getType()) {
                case COPPER -> 5000;
                case IRON -> 10000;
                case GOLD -> 25000;
                default -> 2000;
            };
        }
        if (item instanceof Axe axe) {
            return switch (axe.getType()) {
                case COPPER -> 5000;
                case IRON -> 10000;
                case GOLD -> 25000;
                default -> 2000;
            };
        }
        if (item instanceof Backpack backpack) {
            return switch (backpack.getType()) {
                case BIG -> 10000;
                default -> 2000;
            };
        }
        if (item instanceof TrashCan trashCan) {
            return switch (trashCan.getType()) {
                case COPPER -> 5000;
                case IRON -> 10000;
                case GOLD -> 25000;
                default -> 2000;
            };
        }
        if (item instanceof WateringCan wateringCan) {
            return switch (wateringCan.getType()) {
                case COPPER -> 5000;
                case IRON -> 10000;
                case GOLD -> 25000;
                default -> 2000;
            };
        }
        return 2000;
    }

    public void upgrade(String tool, Label messageLabel) {
        Slot slot = repo.getCurrentGame().getCurrentPlayer().getInventory().getSlot(tool.toLowerCase());
        if (slot == null) {
            messageLabel.setText("You don't have a " + tool + " to upgrade");
            return;
        }
        Item item = slot.getItem();
        int price = getUpgradePrice(item);
        int numOfCoins = repo.getCurrentGame().getCurrentPlayer().getNumOfCoins();

        if (numOfCoins < price) {
            messageLabel.setText("You need " + price + " coins to upgrade");
            return;
        }

        if (item instanceof Hoe hoe) hoe.upgrade();
        else if (item instanceof Pickaxe pickaxe) pickaxe.upgrade();
        else if (item instanceof Axe axe) axe.upgrade();
        else if (item instanceof Backpack backpack) backpack.upgrade();
        else if (item instanceof TrashCan trashCan) trashCan.upgrade();
        else if (item instanceof WateringCan wateringCan) wateringCan.upgrade();

        repo.getCurrentGame().getCurrentPlayer().setNumOfCoins(numOfCoins - price);
        messageLabel.setText("Your" + item.getName() + " has upgraded " + price + " coins");
    }
}
