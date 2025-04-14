package models.building;

import models.Position;
import models.Size;

public class Quarry extends Building {
    public Quarry() {
        super();
    }

    public Quarry(Position topLeftCorner, Size size) {
        super(topLeftCorner, size);
    }
}
