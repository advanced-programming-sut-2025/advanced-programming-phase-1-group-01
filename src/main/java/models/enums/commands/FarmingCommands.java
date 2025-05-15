package models.enums.commands;

public enum FarmingCommands implements Command {
    CRAFT_INFO("craftinfo -n (.+?)"),
    PLANT("plant -s (.+?) -d (.+?)"),
    SHOW_PLANT("showplant -l (.+?)"),
    FERTILIZE("fertilize -f (.+?) -d (.+?)"),
    HOW_MUCH_WATER("howmuch water"),
    ;

    private String regex;

    FarmingCommands(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
