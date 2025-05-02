package models.building;

import java.util.List;

public class Farm extends Map {
    private static final int FARM_HEIGHT = 75;
    private static final int FARM_WIDTH = 75;
    private Lake lake;
    private Cottage cottage;
    private Quarry quarry;
    private Greenhouse greenhouse;

    public List<List<Tile>> getTiles() {
        return tiles;
    }

    private final List<List<Tile>> tiles;

    public Farm(List<List<Tile>> tiles, Lake lake, Cottage cottage, Quarry quarry, Greenhouse greenhouse) {
        this.tiles = tiles;
        this.lake = lake;
        this.cottage = cottage;
        this.quarry = quarry;
        this.greenhouse = greenhouse;
    }

    public int getHeight() {
        return FARM_HEIGHT;
    }

    public int getWidth() {
        return FARM_WIDTH;
    }
}