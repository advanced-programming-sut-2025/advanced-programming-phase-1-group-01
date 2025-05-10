package models.enums.commands;

public enum RelationshipCommands {
    SHOW_FRIENDSHIPS("friendships"),
    TALK("talk -u (.+?) -m (.+?)"),
    TALK_HISTORY("talk history -u (.+?)"),
    GIFT("gift -u (.+?) -i (.+?) -a (.+?)"),
    GIFT_LIST("gift list"),
    GIFT_RATE("gift rate -i (.+?) -r (.+?)"),
    GIFT_HISTORY("gift history -u (.+?)"),
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
