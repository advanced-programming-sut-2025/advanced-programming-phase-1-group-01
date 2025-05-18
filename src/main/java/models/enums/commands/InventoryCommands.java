package models.enums.commands;

public enum InventoryCommands implements Command {
    INVENTORY_SHOW("inventory show"),
    INVENTORY_TRASH_1("inventory trash -i (.+?) -n (.+?)"),
    INVENTORY_TRASH_2("inventory trash -i (.+?)"),
    CHEAT_COINS("cheat add (.+?) dollars"),
    CHEAT_ADD_ITEM("cheat add item -n (.+?) -c (.+?)"),
    SHOW_COINS("show coins"),
    ;

    private final String regex;

    InventoryCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}