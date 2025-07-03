package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.enums.commands.Command;

public enum PierreCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products of pierre"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products of pierre"),
    PIERRE_STORE("purchase pierre (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    PierreCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
