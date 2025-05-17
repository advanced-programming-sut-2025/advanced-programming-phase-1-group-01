package models.enums.commands;

public enum CookingCommands implements Command {

    SHOW_RECIPE("cooking show recipes"),
    CHEAT_ADD_RECIPE("cheat add cooking recipe -r (.+?)"),
    PUT_REFRIGERATOR("cooking refrigerator put (.+?) -a (.+?)"),
    PICK_REFRIGERATOR("cooking refrigerator pick (.+?) -a (.+?)"),
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
