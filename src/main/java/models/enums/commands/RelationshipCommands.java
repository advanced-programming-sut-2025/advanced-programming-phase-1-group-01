package models.enums.commands;

public enum RelationshipCommands {
    SHOW_FRIENDSHIPS("friendships"),
    TALK("talk -u (.+?) -m (.+?)"),
    TALK_HISTORY("talk history -u (.+?)"),
    GIFT("gift -u (.+?) -i (.+?) -a (.+?)"),
    GIFT_LIST("gift -i (.+?) -r (.+?)"),
    GIFT_RATE(""),
    GIFT_HISTORY(""),
    HUG(""),
    FLOWER(""),
    ASK_MARRIAGE(""),
    RESPOND_MARRIAGE(""),
    START_TRADE(""),
    TRADE(""),
    TRADE_LIST(""),
    TRADE_RESPONSE(""),
    TRADE_HISTORY("");

    RelationshipCommands(String regex) {
        this.regex = regex;
    }

    private final String regex;

    public String getRegex() {
        return regex;
    }
}
