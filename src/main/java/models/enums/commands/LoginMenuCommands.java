package models.enums.commands;

public enum LoginMenuCommands implements Command {
    MENU_ENTER("menu enter (.+?)"),
    MENU_EXIT("menu exit"),
    SHOW_CURRENT_MENU("show current menu"),
    REGISTER("register -u (.+?) -p (.+?) -n (.+?) -e (.+?) -g (.+?)"),
    PICK_QUESTION("pick question -q (.+?) -a (.+?) -c (.+?)"),
    LOGIN("login -u (.+?) -p (.+?)(?: -stay-logged-in)?"),
    FORGET_PASSWORD("forget password -u (.+?)"),
    ANSWER("answer -a (.+?)"),
    LOAD_USER("load user"),
    ;

    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
