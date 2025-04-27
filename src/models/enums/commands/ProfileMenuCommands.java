package models.enums.commands;

public enum ProfileMenuCommands {
    MENU_ENTER("menu enter (.+?)"),
    MENU_EXIT("menu exit"),
    SHOW_CURRENT_MENU("show current menu"),
    ;

    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
