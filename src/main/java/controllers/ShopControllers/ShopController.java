package controllers.ShopControllers;

import controllers.Controller;
import models.Result;
import models.data.Repository;
import models.shop.enums.ShopCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ShopController extends Controller {
    public ShopController(Repository repo) {
        super(repo);
    }

    protected Result cheatCoins(String command) {
        String[] tokens = command.split(" ");
        int count = Integer.parseInt(tokens[2]);
        repo.getCurrentGame().getCurrentPlayer().increaseCoins(count);
        return new Result(true, "coins have been added to your balance: " + count);
    }

    protected String extractValue(String command, String startFlag, String endFlag) {
        String patternString;

        if (endFlag != null) {
            patternString = startFlag + " (.*?) " + endFlag;
        } else {
            patternString = startFlag + " (.*)";
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return null;
    }

    protected abstract boolean isShopOpen(int hour);

    protected abstract Result purchase(String command);

    protected abstract Result showAllAvailableProducts();

    protected abstract Result showAllProducts();

}
