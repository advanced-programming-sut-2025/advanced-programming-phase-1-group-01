package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.enums.commands.Command;

public enum JojaMartCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products of jojamart"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products of jojamart"),
    JOJA_MART("purchase jojamart (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    JojaMartCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
