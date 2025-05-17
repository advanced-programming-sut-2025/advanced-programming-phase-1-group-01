package models.shop.enums;

import models.enums.commands.Command;

public enum JojaMartCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products"),
    JOJA_MART("purchase jojamart (.+?) -n (.+?)"),
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
