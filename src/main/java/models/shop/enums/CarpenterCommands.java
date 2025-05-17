package models.shop.enums;

import models.enums.commands.Command;

public enum CarpenterCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products of carpenter"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products of carpenter"),
    CARPENTER("purchase pierre (.+?) -n (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    ;

    private final String regex;

    CarpenterCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
