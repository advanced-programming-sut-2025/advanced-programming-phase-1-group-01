package com.stardew_valley.models.building;

import com.stardew_valley.models.Position;
import com.stardew_valley.models.Size;

public class Room2 extends Building {
    private static final int Room2_WIDTH = 6;
    private static final int Room2_HEIGHT = 6;

    public Room2() {super();}

    public Room2(Position position) {
        super(position, new Size(Room2_WIDTH, Room2_HEIGHT));
    }
}
