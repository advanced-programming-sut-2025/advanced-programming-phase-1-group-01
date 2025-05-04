package models.building;

import models.farming.Plant;

import java.util.ArrayList;
import java.util.List;

public class Farm extends Map {
    private static final int FARM_HEIGHT = 75;
    private static final int FARM_WIDTH = 75;
    private Lake lake;
    private Greenhouse greenhouse;
    private Cottage cottage;
    private Quarry quarry;

    public List<List<Tile>> getTiles() {
        return tiles;
    }

    public Farm(List<List<Tile>> tiles, Lake lake, Cottage cottage, Quarry quarry, Greenhouse greenhouse) {
        super(tiles);
        this.lake = lake;
        this.cottage = cottage;
        this.quarry = quarry;
        this.greenhouse = greenhouse;
    }

    public Lake getLake() {
        return lake;
    }

    public void setLake(Lake lake) {
        this.lake = lake;
    }

    public Greenhouse getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Quarry getQuarry() {
        return quarry;
    }

    public void setQuarry(Quarry quarry) {
        this.quarry = quarry;
    }
}