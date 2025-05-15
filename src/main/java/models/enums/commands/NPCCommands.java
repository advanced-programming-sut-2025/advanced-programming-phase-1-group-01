package models.enums.commands;

public enum NPCCommands {
    MEET_NPC("meet NPC -n (?<name>\\S+) -m (?<message>\\S+)"),
    GIFT_NPC("gift NPC (?<name>\\S+) -i (?<item>\\S+)"),
    FRIENDSHIP_NPC_LIST("friendship NPC list"),
    QUESTS_LIST("quests list"),
    QUEST_FINISH("quest finish -i (?<index>\\S+)");

    private final String regex;

    private NPCCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
