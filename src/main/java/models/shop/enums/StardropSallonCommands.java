package models.shop.enums;

import models.enums.commands.Command;

public enum StardropSallonCommands implements Command {
    SHOW_ALL_PRODUCTS("show all products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show all available products"),
    STAR_DROP_SALOON("purchase stardrop saloon (.+?) -n (.+?)"),
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
