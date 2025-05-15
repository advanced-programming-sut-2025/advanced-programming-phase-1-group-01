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
    private static final int GREENHOUSE_WIDTH = 8;
    private static final int GREENHOUSE_HEIGHT = 7;

    private static final int GREENHOUSE_BUILD_WOOD_NEEDED = 500;
    private static final int GREENHOUSE_BUILD_COIN_NEEDED = 1000;

    public Greenhouse() {
        super();
    }

    public Greenhouse(Position topLeftCorner) {
        super(topLeftCorner, new Size(GREENHOUSE_WIDTH, GREENHOUSE_HEIGHT));
    }
}
