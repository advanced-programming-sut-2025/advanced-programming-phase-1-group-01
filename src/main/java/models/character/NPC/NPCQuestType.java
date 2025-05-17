package models.character.NPC;

import models.Item;
import models.cooking.*;
import models.crafting.BeeHouse;
import models.crafting.DeluxeScarecrow;
import models.crafting.IridiumSprinkler;
import models.Plants.Plant;
import models.ingredients.*;

public enum NPCQuestType {
    SEBASTIAN_1(1),
    SEBASTIAN_2(2),
    SEBASTIAN_3(3),
    ABIGAIL_1(4),
    ABIGAIL_2(5),
    ABIGAIL_3(6),
    HARVEY_1(7),
    HARVEY_2(8),
    HARVEY_3(9),
    LEAH_1(10),
    LEAH_2(11),
    LEAH_3(12),
    ROBIN_1(13),
    ROBIN_2(14),
    ROBIN_3(15);


    private final int missionNumber;


    NPCQuestType(int missionNumber) {
        this.missionNumber = missionNumber;
    }

    public int getMissionNumber() {
        return missionNumber;
    }

}