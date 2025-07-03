package com.stardew_valley.models.building;

import com.stardew_valley.models.Size;
import com.stardew_valley.models.character.NPC.NPCVillage;

import java.util.List;

public class Settlement extends Maps {
    private final int MAP_WIDTH = 0;
    private final int MAP_HEIGHT = 0;

    private final Size size;
    private int numOfFarms;
    private List<Farm> farms;
    private NPCVillage npcVillage;

    public Settlement(List<List<Tile>> tiles, int numOfFarms) {
        super(tiles);
        this.numOfFarms = numOfFarms;
        this.size = new Size(MAP_WIDTH, MAP_HEIGHT);
    }

    public void initFarms() {}
}
