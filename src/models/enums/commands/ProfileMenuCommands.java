package models.enums.commands;

public enum ProfileMenuCommands {
    MENU_ENTER("menu enter (.+?)"),
    MENU_EXIT("menu exit"),
    SHOW_CURRENT_MENU("show current menu"),
    CHANGE_USERNAME("change username -u (.+?)"),
    CHANGE_NICKNAME("change nickname -n (.+?)"),
    CHANGE_EMAIL("change email -e (.+?)"),
    CHANGE_PASSWORD("change password -p (.+?) -o (.+?)"),
    USER_INFO("user info")
    ;

    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
