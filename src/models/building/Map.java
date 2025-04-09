package models.building;

import models.Size;

public class Map {
    private final int MAP_WIDTH = 0;
    private final int MAP_HEIGHT = 0;

    private final Size size;
    private int numOfFarms;

    public Map(int numOfFarms) {
        this.numOfFarms = numOfFarms;
        this.size = new Size(MAP_WIDTH, MAP_HEIGHT);
    }
}
