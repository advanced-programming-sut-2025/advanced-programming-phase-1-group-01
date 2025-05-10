package controllers.ShopControllers;

import controllers.Controller;
import models.Result;
import models.data.Repository;
import models.shop.enums.ShopCommands;

public abstract class ShopController extends Controller {
    public ShopController(Repository repo) {
        super(repo);
    }

    public Result handleCommand(String command) {

        int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();

        if (!isShopOpen(hour)) {
            return new Result(false, "shop is closed");
        }

        ShopCommands matchedCommand = null;

        for (ShopCommands cmd : ShopCommands.values()) {
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
            case PURCHASE:
                return purchase(command);
            case CHEAT_COINS:
                return cheatCoins(command);
        }

        return null;
    }

    protected Result cheatCoins(String command) {
        String[] tokens = command.split(" ");
        int count = Integer.parseInt(tokens[2]);
        repo.getCurrentGame().getCurrentPlayer().increase(count);
        return new Result(true, "coins have been added to your balance: " + count);
    }

    protected abstract boolean isShopOpen(int hour);

    protected abstract Result purchase(String command);

    protected abstract Result showAllAvailableProducts();

    protected abstract Result showAllProducts();

}
