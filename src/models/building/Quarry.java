package models.building;

import models.Position;
import models.Size;

public class Quarry extends Building {
    public Quarry() {
        super();
    }
    private static final int LAKE_HEIGHT = 12;
    private static final int LAKE_WIDTH = 15;

    public Quarry(Position position) {
        super(position, new Size(LAKE_WIDTH, LAKE_HEIGHT));
    }
}
