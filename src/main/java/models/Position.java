package models;

import models.enums.Direction;

public record Position(int x, int y) {
    public Position applyDirection(Direction direction) {
        return switch (direction) {
            case UP -> new Position(x, y - 1);
            case DOWN -> new Position(x, y + 1);
            case LEFT -> new Position(x - 1, y);
            case RIGHT -> new Position(x + 1, y);
            case UP_LEFT -> new Position(x - 1, y - 1);
            case UP_RIGHT -> new Position(x + 1, y - 1);
            case DOWN_LEFT -> new Position(x - 1, y + 1);
            case DOWN_RIGHT -> new Position(x + 1, y + 1);
        };
    }
}
