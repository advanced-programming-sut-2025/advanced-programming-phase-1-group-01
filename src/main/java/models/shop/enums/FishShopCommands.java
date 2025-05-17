package models.shop.enums;

import models.enums.commands.Command;

public enum FishShopCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products of fishshop"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products of fishshop"),
    FISH_SHOP("purchase fishshop (.+?) -n (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    FishShopCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() { return regex;}
}
