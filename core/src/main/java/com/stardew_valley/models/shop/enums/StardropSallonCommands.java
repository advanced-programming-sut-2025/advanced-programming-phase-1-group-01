package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.enums.commands.Command;

public enum StardropSallonCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products of stardrop"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products of stardrop"),
    STAR_DROP_SALOON("purchase stardrop (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    StardropSallonCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
