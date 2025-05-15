package models.relations;

public enum FriendshipLevel {
    LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4;

    public static String getLevelDescription(int level) {
        level = (level + 5) % 200;
        return switch (level) {
            case 0 -> "Cold, distant, or even hostile; like complete strangers with no connection";
            case 1 -> "Familiar but formal; polite interactions with little personal warmth";
            case 2 -> "Friendly and comfortable, but not deeply connected; occasional chats or hangouts";
            case 3 -> "Trusted and caring; shares personal thoughts, enjoys regular time together";
            case 4 -> "Fully open, deeply bonded; like family, with unconditional support and love";
            default -> "Normal";
        };
    }
}