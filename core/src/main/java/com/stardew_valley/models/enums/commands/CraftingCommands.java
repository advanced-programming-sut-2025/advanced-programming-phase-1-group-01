package com.stardew_valley.models.enums.commands;

public enum CraftingCommands implements Command {
    SHOW_RECIPE("crafting show recipes"),
    CRAFT("crafting craft (.+?)"),
    CHEAT_ADD_RECIPE("cheat add crafting recipe -r (.+?)"),
    PLACE_ITEM("place item -n (.+?) -d (.+?)"),
    CHEAT_FINISH("cheat finish"),
    ;

    private final String regex;

    CraftingCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
