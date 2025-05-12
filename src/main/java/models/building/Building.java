package models.building;

import models.Position;
import models.Size;

import java.util.ArrayList;
import java.util.List;

public abstract class Building {
    protected Position topLeftCorner;
    protected Size size;
    protected List<List<Tile>> tiles;

    public Building() {}

    public Building(Position topLeftCorner, Size size) {
        this.topLeftCorner = topLeftCorner;
        this.size = size;
        this.tiles = new ArrayList<>();
    }

    public Position getTopLeftCorner() {
        return topLeftCorner;
    }

    public Position getBottomRightCorner() {
        return new Position(topLeftCorner.x() + size.getWidth(), topLeftCorner.y() + size.getHeight());
    }

    public boolean isThatTileEmpty(Position position) {
        return tiles.get(position.x()).get(position.y()).isEmpty();
    }
}