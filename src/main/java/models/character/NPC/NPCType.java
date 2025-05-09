package models.character.NPC;

public enum NPCType {
    SEBASTIAN("Sebastian"), ABIGAIL("Abigail"), HARVEY("Harvey"), LEAH("Leah"), ROBIN("Robin");

    private final String name;

    NPCType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
