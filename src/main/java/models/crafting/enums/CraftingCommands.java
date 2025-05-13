package models.crafting.enums;

public enum CraftingCommands {
    SHOW_RECIPE("crafting show recipe"),
    CRAFT("crafting craft (.+?)"),
    CHEAT_ADD_ITEM("cheat add item -n (.+?) -c (.+?)"),
    CHEAT_ADD_RECIPE("cheat add recipe -r (.+?)"),
    ;

    private final String regex;

    CraftingCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
