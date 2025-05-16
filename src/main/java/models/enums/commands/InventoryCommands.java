package models.enums.commands;

public enum InventoryCommands implements Command {
    INVENTORY_SHOW("inventory show"),
    INVENTORY_TRASH_1("inventory trash -i (.+?) -n (.+?)"),
    INVENTORY_TRASH_2("inventory trash -i (.+?)"),;
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
