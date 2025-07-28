package com.stardew_valley.models.enums;

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
//        return switch (this) {
//            case UP -> "up";
//            case DOWN -> "down";
//            case LEFT -> "left";
//            case RIGHT -> "right";
//            case UP_LEFT -> "up left";
//            case UP_RIGHT -> "up right";
//            case DOWN_LEFT -> "down left";
//            case DOWN_RIGHT -> "down right";
//        };
        switch (this) {
            case UP:
                return "up";
            case DOWN:
                return "down";
            case LEFT:
                return "left";
            case RIGHT:
                return "right";
            case UP_LEFT:
                return "up left";
            case UP_RIGHT:
                return "up right";
            case DOWN_LEFT:
                return "down left";
            case DOWN_RIGHT:
                return "down right";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
