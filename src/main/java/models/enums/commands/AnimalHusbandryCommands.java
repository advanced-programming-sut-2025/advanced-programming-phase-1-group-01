package models.enums.commands;

public enum AnimalHusbandryCommands implements Command {
    BUILD("build -a (?<name>\\S+) -l <(?<X>\\S+),(?<Y>\\S+)>"),
    BUY_ANIMAL("buy animal -a (?<animal>\\S+) -n (?<name>\\S+)"),
    PET("pet -n (?<name>\\S+)"),
    ANIMALS("animals"),
    SHEPHERD_ANIMAL("shepherd animal -n (?<name>\\S+) -l <(?<X>\\S+),(?<Y>\\S+)>"),
    FEED_HAY("feed hay -n (?<name>\\S+)"),
    PRODUCES("produces"),
    COLLECT_PRODUCE("collect produces -n (?<name>\\S+)"),
    SELL_ANIMAL("sell animal -n (?<name>\\S+)"),
    CHEAT_CODE("cheat set friendship -n (?<name>\\S+) -c (?<amount>\\S+)");

    private final String regex;

    private AnimalHusbandryCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
