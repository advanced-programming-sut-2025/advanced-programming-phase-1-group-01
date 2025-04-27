package models.enums.commands;

public enum LoginMenuCommands {
    MENU_ENTER("menu enter (.+?)"),
    MENU_EXIT("menu exit"),
    SHOW_CURRENT_MENU("show current menu"),
    REGISTER("register -u (.+?) -p (.+?) -n (.+?) -e (.+?) -g (.+?)"),
    PICK_QUESTION("pick question -q (.+?) -a (.+?) -c (.+?)"),
    LOGIN("login -u (.+?) -p (.+?)(?: -stay-logged-in)?"),
    FORGET_PASSWORD("forget password -u (.+?)"),
    ANSWER("answer -a (.+?)"),
    USER_LOGOUT(""),
    ;

    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
