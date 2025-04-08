package models.character.NPC;

import models.character.Character;

import java.util.HashMap;
import java.util.Map;

public class NPC extends Character {
    NPCType type;
    NPCHome home;
    NPCVillage village;
    final Map<Integer, NPCQuest> quests;

    public NPC(NPCType type, NPCHome home, NPCVillage village) {
        this.type = type;
        this.home = home;
        this.village = village;
        quests = new HashMap<>();
        initQuests();
    }

    private void initQuests() {
        if (type == NPCType.SEBASTIAN) {
            quests.put(1, NPCQuest.SEBASTIAN_1);
            quests.put(2, NPCQuest.SEBASTIAN_2);
            quests.put(3, NPCQuest.SEBASTIAN_3);
        } else if (type == NPCType.ABIGAIL) {
            quests.put(1, NPCQuest.ABIGAIL_1);
            quests.put(2, NPCQuest.ABIGAIL_2);
            quests.put(3, NPCQuest.ABIGAIL_3);
        } else if (type == NPCType.HARVEY) {
            quests.put(1, NPCQuest.HARVEY_1);
            quests.put(2, NPCQuest.HARVEY_2);
            quests.put(3, NPCQuest.HARVEY_3);
        } else if (type == NPCType.LEAH) {
            quests.put(1, NPCQuest.LEAH_1);
            quests.put(2, NPCQuest.LEAH_2);
            quests.put(3, NPCQuest.LEAH_3);
        } else if (type == NPCType.ROBIN) {
            quests.put(1, NPCQuest.ROBIN_1);
            quests.put(2, NPCQuest.ROBIN_2);
            quests.put(3, NPCQuest.ROBIN_3);
        }
    }
}