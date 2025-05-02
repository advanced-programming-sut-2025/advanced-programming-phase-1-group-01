package models.building;

import models.Position;

public class Tile {
    private final Position position;
    private TileType type;
    private boolean isMovable;
    private Building building;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    private Object content;

    public Tile(TileType type, Position position, boolean isMovable) {
        this.position = position;
        this.type = type;
        this.isMovable = isMovable;
        this.building = null;
    }

    public Position getPosition() {
        return position;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
