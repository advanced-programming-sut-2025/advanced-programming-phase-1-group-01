package models.enums.commands;

public enum GameMenuCommands {
    MENU_ENTER("menu enter (.+?)"),
    MENU_EXIT("menu exit"),
    SHOW_CURRENT_MENU("show current menu"),
    ;

    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public String regex() {
        return regex;
    }
}