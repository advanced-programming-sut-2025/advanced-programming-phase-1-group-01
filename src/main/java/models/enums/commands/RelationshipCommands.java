package models.enums.commands;

public enum RelationshipCommands implements Command {
    SHOW_FRIENDSHIPS("friendships"),
    TALK("talk -u (.+?) -m (.+?)"),
    TALK_HISTORY("talk history -u (.+?)"),
    HUG("hug -u (.+?)"),
    GIFT("gift -u (.+?) -i (.+?) -a (.+?)"),
    GIFT_LIST("gift list"),
    GIFT_RATE("gift rate -i (.+?) -r (.+?)"),
    GIFT_HISTORY("gift history -u (.+?)"),
    FLOWER("flower -u (.+?)"),
    ASK_MARRIAGE(" ask marriage -u (.+?) -r (.+?)"),
    RESPOND_MARRIAGE("respond (.+?) -u (.+?)"),
    START_TRADE("start trade"),
    TRADE_REQUEST("trade -u (.+?) -t request -i (.+?) -a (.+?) -p (.+?)"),
    TRADE_OFFER("trade -u (.+?) -t offer -i (.+?) -a (.+?) -ti (.+?) -ta (.+?)"),
    TRADE_LIST("trade list"),
    TRADE_RESPONSE("trade response (.+?) -i (.+?)"),
    TRADE_HISTORY("trade history"),;

    RelationshipCommands(String regex) {
        this.regex = regex;
    }

    private final String regex;

    @Override
    public String getRegex() {
        return regex;
    }
}
