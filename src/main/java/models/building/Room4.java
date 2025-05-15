package models.building;

import models.Position;
import models.Size;

public class Room4 extends Building {
    private static final int Room4_WIDTH = 6;
    private static final int Room4_HEIGHT = 6;

    public Room4() {super();}

    public Room4(Position position) {
        super(position, new Size(Room4_WIDTH, Room4_HEIGHT));
    }
}
