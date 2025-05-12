package models.enums.commands;

public enum FarmingCommands {
    CRAFT_INFO("craftinfo -n (.+?)"),
    PLANT("plant -s (.+?) -d (.+?)"),
    SHOW_PLANT("showplant -l (.+?)"),
    FERTILIZE("fertilize -f (.+?) -d (.+?)"),
    ;

    private String regex;

    FarmingCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
