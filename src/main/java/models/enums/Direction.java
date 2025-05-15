package models.enums;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT;

    public static Direction fromString(String string) {
        for (Direction direction : values()) {
            if (direction.toString().equals(string)) {
                return direction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return switch (this) {
            case UP -> "up";
            case DOWN -> "down";
            case LEFT -> "left";
            case RIGHT -> "right";
            case UP_LEFT -> "up left";
            case UP_RIGHT -> "up right";
            case DOWN_LEFT -> "down left";
            case DOWN_RIGHT -> "down right";
        };
    }
}
