package models.building;

import models.Size;

import java.util.List;

public class Map {
    private final int MAP_WIDTH = 0;
    private final int MAP_HEIGHT = 0;

    private final Size size;
    private int numOfFarms;
    private List<Farm> farms;

    public Map(int numOfFarms) {
        this.numOfFarms = numOfFarms;
        this.size = new Size(MAP_WIDTH, MAP_HEIGHT);
    }

    public void initFarms() {}
}
