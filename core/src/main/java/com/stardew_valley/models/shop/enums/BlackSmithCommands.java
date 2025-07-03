package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.enums.commands.Command;

public enum BlackSmithCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products of blacksmith"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products of blacksmith"),
    BLACKSMITH("purchase blacksmith (.+?)"),
    TOOLS_UPGRADE("tools upgrade (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    BlackSmithCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
