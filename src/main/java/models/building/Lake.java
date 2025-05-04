package models.building;

import models.Position;
import models.Size;

public class Lake extends Building {
    public Lake() {
        super();
    }

    private static final int LAKE_HEIGHT = 3;
    private static final int LAKE_WIDTH = 3;

    public Lake(Position position) {
        super(position, new Size(LAKE_WIDTH, LAKE_HEIGHT));
    }
}
