package com.stardew_valley.models.building;

import com.stardew_valley.models.Position;
import com.stardew_valley.models.Size;

public class Room1 extends Building {
    private static final int Room1_WIDTH = 6;
    private static final int Room1_HEIGHT = 6;

    public Room1() {super();}

    public Room1(Position position) {
        super(position, new Size(Room1_WIDTH, Room1_HEIGHT));
    }
}
