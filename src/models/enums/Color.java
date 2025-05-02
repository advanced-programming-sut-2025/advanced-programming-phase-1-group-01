package models.enums;

public enum Color {
    // Regular Colors
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    // Bold
    BLACK_BOLD("\u001B[1;30m"),
    RED_BOLD("\u001B[1;31m"),
    GREEN_BOLD("\u001B[1;32m"),
    YELLOW_BOLD("\u001B[1;33m"),
    BLUE_BOLD("\u001B[1;34m"),
    PURPLE_BOLD("\u001B[1;35m"),
    CYAN_BOLD("\u001B[1;36m"),
    WHITE_BOLD("\u001B[1;37m"),

    // Underline
    BLACK_UNDERLINED("\u001B[4;30m"),
    RED_UNDERLINED("\u001B[4;31m"),
    GREEN_UNDERLINED("\u001B[4;32m"),
    YELLOW_UNDERLINED("\u001B[4;33m"),
    BLUE_UNDERLINED("\u001B[4;34m"),
    PURPLE_UNDERLINED("\u001B[4;35m"),
    CYAN_UNDERLINED("\u001B[4;36m"),
    WHITE_UNDERLINED("\u001B[4;37m"),

    // Background
    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    WHITE_BACKGROUND("\u001B[47m"),

    // High Intensity
    BLACK_BRIGHT("\u001B[90m"),
    RED_BRIGHT("\u001B[91m"),
    GREEN_BRIGHT("\u001B[92m"),
    YELLOW_BRIGHT("\u001B[93m"),
    BLUE_BRIGHT("\u001B[94m"),
    PURPLE_BRIGHT("\u001B[95m"),
    CYAN_BRIGHT("\u001B[96m"),
    WHITE_BRIGHT("\u001B[97m"),

    // Bold High Intensity
    BLACK_BOLD_BRIGHT("\u001B[1;90m"),
    RED_BOLD_BRIGHT("\u001B[1;91m"),
    GREEN_BOLD_BRIGHT("\u001B[1;92m"),
    YELLOW_BOLD_BRIGHT("\u001B[1;93m"),
    BLUE_BOLD_BRIGHT("\u001B[1;94m"),
    PURPLE_BOLD_BRIGHT("\u001B[1;95m"),
    CYAN_BOLD_BRIGHT("\u001B[1;96m"),
    WHITE_BOLD_BRIGHT("\u001B[1;97m"),

    // High Intensity backgrounds
    BLACK_BACKGROUND_BRIGHT("\u001B[100m"),
    RED_BACKGROUND_BRIGHT("\u001B[101m"),
    GREEN_BACKGROUND_BRIGHT("\u001B[102m"),
    YELLOW_BACKGROUND_BRIGHT("\u001B[103m"),
    BLUE_BACKGROUND_BRIGHT("\u001B[104m"),
    PURPLE_BACKGROUND_BRIGHT("\u001B[105m"),
    CYAN_BACKGROUND_BRIGHT("\u001B[106m"),
    WHITE_BACKGROUND_BRIGHT("\u001B[107m"),

    // Additional styles
    BOLD("\u001B[1m"),
    UNDERLINE("\u001B[4m"),
    REVERSED("\u001B[7m"),
    RESET("\u001B[0m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String colorize(String text) {
        return code + text + RESET.code;
    }
}