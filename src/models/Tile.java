package models;

public class Tile {
    private final Position position;
    private TileType type;

    public Tile(TileType type, Position position) {
        this.position = position;
        this.type = type;
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
}
