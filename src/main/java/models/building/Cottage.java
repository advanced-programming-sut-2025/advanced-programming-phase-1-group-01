package models.building;

import models.Position;
import models.Size;

public class Cottage extends Building {
    private static final int COTTAGE_WIDTH = 6;
    private static final int COTTAGE_HEIGHT = 6;

    public Cottage() {
        super();
    }

    public Cottage(Position position) {
        super(position, new Size(COTTAGE_WIDTH, COTTAGE_HEIGHT));
    }
}
