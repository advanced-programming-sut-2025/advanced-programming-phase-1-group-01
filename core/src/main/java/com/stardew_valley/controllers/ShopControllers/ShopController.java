package com.stardew_valley.controllers.ShopControllers;

import com.stardew_valley.controllers.Controller;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.shop.Shop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ShopController extends Controller {
    public ShopController(Repository repo) {
        super(repo);
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

    protected boolean isNear(Player player, Shop shop) {
            int dx = Math.abs(player.getPosition().x() - shop.getX());
            int dy = Math.abs(player.getPosition().y() - shop.getY());
            double distance = Math.sqrt(dx * dx + dy * dy);
            return distance <= 1;
    }

    protected abstract boolean isShopOpen(int hour);

    protected abstract Result purchase(String command);

    protected abstract Result showAllAvailableProducts();

    protected abstract Result showAllProducts();

}
