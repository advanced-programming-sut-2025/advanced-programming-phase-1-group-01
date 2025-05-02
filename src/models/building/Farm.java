package models.building;

import java.util.List;

public class Farm extends Map {
    private Lake lake;
    private Greenhouse greenhouse;
    private Cottage cottage;
    private Quarry quarry;

    public Farm() {
        lake = new Lake();
        greenhouse = new Greenhouse();
        cottage = new Cottage();
        quarry = new Quarry();
        initBuildings();
    }

    private void initBuildings() {}

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