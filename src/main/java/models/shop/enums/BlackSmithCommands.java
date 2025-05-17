package models.shop.enums;

import models.enums.commands.Command;

public enum BlackSmithCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products"),
    BLACKSMITH("purchase blacksmith (.+?) -n (.+?)"),
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
