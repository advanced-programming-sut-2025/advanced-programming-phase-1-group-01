package models.building;

import models.Position;
import models.Size;

public class Room3 extends Building {
    private static final int Room3_WIDTH = 6;
    private static final int Room3_HEIGHT = 6;

    public Room3() {super();}

    public Room3(Position position) {
        super(position, new Size(Room3_WIDTH, Room3_HEIGHT));
    }
}
