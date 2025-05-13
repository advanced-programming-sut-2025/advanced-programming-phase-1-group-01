package models.enums.commands;

public enum ToolCommands implements Command {
    TOOLS_EQUIP("tools equip (.+?)"),
    TOOLS_SHOW_CURRENT("tools show current"),
    TOOLS_SHOW_AVAILABLE("tools show available"),
    TOOLS_UPGRADE("tools upgrade (.+?)"),
    TOOLS_USE("tools use -d (.+?)"),
    ;

    private final String regex;

    ToolCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
