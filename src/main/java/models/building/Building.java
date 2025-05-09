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
}