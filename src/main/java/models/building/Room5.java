package models.building;

import models.Position;
import models.Size;

public class Room5 extends Building {
    private static final int Room5_WIDTH = 6;
    private static final int Room5_HEIGHT = 6;

    public Room5() {super();}

    public Room5(Position position) {
        super(position, new Size(Room5_WIDTH, Room5_HEIGHT));
    }
}
