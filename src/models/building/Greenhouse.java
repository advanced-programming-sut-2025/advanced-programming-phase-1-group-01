package models.building;

import models.Position;
import models.Size;

// no season dependence to plant
// no need to scarecrow
// crucial water even in rainy weather
// local water repository in northern wall
// thor doesn't affect products
// giant products can't grow

public class Greenhouse extends Building {
    private Position tlc;
    private Size size;

    private final int GREENHOUSE_WIDTH = 8;
    private final int GREENHOUSE_HEIGHT = 7;

    public Greenhouse(Position tlc) {
        this.tlc = tlc;
        this.size = new Size(GREENHOUSE_WIDTH, GREENHOUSE_HEIGHT);
    }
}
