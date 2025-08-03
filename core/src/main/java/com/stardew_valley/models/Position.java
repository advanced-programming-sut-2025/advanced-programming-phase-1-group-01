package com.stardew_valley.models;

import com.stardew_valley.models.enums.Direction;

public record Position(int x, int y) {
    public Position applyDirection(Direction direction) {
//        return switch (direction) {
//            case UP -> new Position(x - 1, y);
//            case DOWN -> new Position(x + 1, y);
//            case LEFT -> new Position(x, y - 1);
//            case RIGHT -> new Position(x, y + 1);
//            case UP_LEFT -> new Position(x - 1, y - 1);
//            case UP_RIGHT -> new Position(x - 1, y + 1);
//            case DOWN_LEFT -> new Position(x + 1, y - 1);
//            case DOWN_RIGHT -> new Position(x + 1, y + 1);
//        };
        return switch (direction) {
            case UP -> new Position(x, y + 1);
            case DOWN -> new Position(x, y - 1);
            case LEFT -> new Position(x - 1, y);
            case RIGHT -> new Position(x + 1, y);
            case UP_LEFT -> new Position(x - 1, y + 1);
            case UP_RIGHT -> new Position(x + 1, y + 1);
            case DOWN_LEFT -> new Position(x - 1, y - 1);
            case DOWN_RIGHT -> new Position(x + 1, y - 1);
        };
//        switch (direction) {
//            case UP:
//                return new Position(x - 1, y);
//            case DOWN:
//                return new Position(x + 1, y);
//            case LEFT:
//                return new Position(x, y - 1);
//            case RIGHT:
//                return new Position(x, y + 1);
//            case UP_LEFT:
//                return new Position(x - 1, y - 1);
//            case UP_RIGHT:
//                return new Position(x - 1, y + 1);
//            case DOWN_LEFT:
//                return new Position(x + 1, y - 1);
//            case DOWN_RIGHT:
//                return new Position(x + 1, y + 1);
//            default:
//                throw new IllegalArgumentException("Unknown direction: " + direction);
//        }

    }

    @Override
    public String toString() {
        return "<%d, %d>".formatted(x, y);
    }
}
