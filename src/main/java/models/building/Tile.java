package models.building;

import models.Position;

public class Tile {
    private final Position position;
    private TileType type;
    private boolean isPlowed;
    private boolean isMovable;
    private Building building;
    private TileObject object;

    public Tile(Builder builder) {
        this.position = builder.getPosition();
        this.type = builder.getType();
        this.isMovable = builder.isMovable();
        this.building = builder.getBuilding();
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

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean plow() {
        if (type != TileType.RIVER && isEmpty()) isPlowed = true;
        return false;
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

    public TileObject getObject() {
        return object;
    }

    public void setObject(TileObject object) {
        this.object = object;
    }

    public void removeObject() {
        this.object = null;
    }

    public boolean isEmpty() {
        return object == null;
    }

    public static class Builder {
        private Position position;
        private TileType type;
        private boolean isMovable;
        private Building building;
        private TileObject object;

        public Builder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder setType(TileType type) {
            this.type = type;
            return this;
        }

        public Builder setMovable(boolean movable) {
            isMovable = movable;
            return this;
        }

        public Builder setBuilding(Building building) {
            this.building = building;
            return this;
        }

        public Position getPosition() {
            return position;
        }

        public TileType getType() {
            return type;
        }

        public boolean isMovable() {
            return isMovable;
        }

        public Building getBuilding() {
            return building;
        }

        public Tile build() {
            return new Tile(this);
        }

        public TileObject getObject() {
            return object;
        }

        public Builder setObject(TileObject object) {
            this.object = object;
            return this;
        }

        public boolean isEmpty() {
            return object == null;
        }
    }
}
