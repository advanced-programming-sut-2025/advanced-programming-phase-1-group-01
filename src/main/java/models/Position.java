package models;

import models.enums.Direction;

public record Position(int x, int y) {
    public Position applyDirection(Direction direction) {
        return switch (direction) {
            case UP -> new Position(x - 1, y);
            case DOWN -> new Position(x + 1, y);
            case LEFT -> new Position(x, y - 1);
            case RIGHT -> new Position(x, y + 1);
            case UP_LEFT -> new Position(x - 1, y - 1);
            case UP_RIGHT -> new Position(x - 1, y + 1);
            case DOWN_LEFT -> new Position(x + 1, y - 1);
            case DOWN_RIGHT -> new Position(x + 1, y + 1);
        };
    }

    @Override
    public String toString() {
        return "<%d, %d>".formatted(x, y);
    }
}
