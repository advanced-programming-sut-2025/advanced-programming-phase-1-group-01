package models.enums.commands;

public enum CookingCommands implements Command {

    SHOW_RECIPE("cooking show recipes"),
    CHEAT_ADD_RECIPE("cheat add cooking recipe -r (.+?)"),
    PUT_REFRIGERATOR("cooking refrigerator put (.+?)"),
    PICK_REFRIGERATOR("cooking refrigerator pick (.+?)"),
    COOKING_PREPARE("cooking prepare (.+?)"),
    EAT("eat (.+?)"),
    ;

    private final String regex;

    CookingCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
