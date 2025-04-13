package models.building;

import models.Position;
import models.Size;
import models.Tile;

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
        for (List<Tile> row : this.tiles) {
            row = new ArrayList<>();
        }
    }
}