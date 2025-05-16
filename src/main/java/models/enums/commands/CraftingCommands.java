package models.enums.commands;

public enum CraftingCommands implements Command {
    SHOW_RECIPE("crafting show recipe"),
    CRAFT("crafting craft (.+?)"),
    CHEAT_ADD_ITEM("cheat add item -n (.+?) -c (.+?)"),
    CHEAT_ADD_RECIPE("cheat add recipe -r (.+?)"),
    PLACE_ITEM("place item -n (.+?) - d (.+?)"),
    ;

    private final String regex;

    CraftingCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
