package models.building;

import models.Tile;

import java.util.List;

public class Farm {
    private Lake lake;
    private Greenhouse greenhouse;
    private Cottage cottage;
    private Quarry quarry;
    private List<List<Tile>> tiles;

    public Farm() {
        lake = new Lake();
        greenhouse = new Greenhouse();
        cottage = new Cottage();
        quarry = new Quarry();
        initBuildings();
    }

    private void initBuildings() {}
}