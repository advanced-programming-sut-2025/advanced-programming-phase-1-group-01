package models;

public class Tile {
    private final Position position;
    private TileType type;
    private boolean isMovable;

    public Tile(TileType type, Position position, boolean isMovable) {
        this.position = position;
        this.type = type;
        this.isMovable = isMovable;
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
}
