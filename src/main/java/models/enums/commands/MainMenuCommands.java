package models.enums.commands;

public enum MainMenuCommands implements Command {
    SHOW_CURRENT_MENU("show current menu"),
    MENU_ENTER_PROFILE_MENU("menu enter profile menu"),
    MENU_Enter_GAME_MENU("menu enter game menu"),
    MENU_EXIT("menu exit"),
    USER_LOGOUT("user logout"),
    ;

    private final String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
