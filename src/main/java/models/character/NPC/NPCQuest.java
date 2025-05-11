package models.character.NPC;

//import models.Plants.Plant;

import models.cooking.*;
import models.crafting.BeeHouse;
import models.crafting.DeluxeScarecrow;
import models.crafting.IridiumSprinkler;
import models.Plants.Plant;
import models.ingredients.*;

public enum NPCQuest {
    SEBASTIAN_1(1, NPCType.SEBASTIAN, new IronBar(50) /*50 Iron*/, new Diamond(50), true),
    SEBASTIAN_2(2, NPCType.SEBASTIAN, new PumpkinPie(1) /*1 PumpkinPie*/, new Coin(5000), false),
    SEBASTIAN_3(3, NPCType.SEBASTIAN, new Stone(150) /*150 Stone*/, new Quartz(50), false),
    ABIGAIL_1(1, NPCType.ABIGAIL, new GoldBar(1) /*1 GoldBar*/, new FriendshipLevel(1), true),
    ABIGAIL_2(2, NPCType.ABIGAIL, new Pumpkin(1) /*1 Pumpkin*/, new Coin(500), false),
    ABIGAIL_3(3, NPCType.ABIGAIL, new Wheat(50) /*50 Wheat*/, new IridiumSprinkler(1), false),
    HARVEY_1(1, NPCType.HARVEY, new Plant(12) /*12 AnyPlant*/, new Coin(750), true),
    HARVEY_2(2, NPCType.HARVEY, new Salmon(1) /*1 SalmonFish*/, new FriendshipLevel(1), false),
    HARVEY_3(3, NPCType.HARVEY, new WineBottle(1) /*1 WineBottle*/, new Salad(5), false),
    LEAH_1(1, NPCType.LEAH, new HardWood(10) /*10 HardWood*/, new Coin(500), true),
    LEAH_2(2, NPCType.LEAH, new Salmon(1) /*1 SalmonFish*/, new SalmonDinner(1), false),
    LEAH_3(3, NPCType.LEAH, new NormalWood(200) /*200 Wood*/, new DeluxeScarecrow(3), false),
    ROBIN_1(1, NPCType.ROBIN, new NormalWood(80) /*80 Wood*/, new Coin(1000), true),
    ROBIN_2(2, NPCType.ROBIN, new IronBar(10) /*10 IronBar*/, new BeeHouse(3), false),
    ROBIN_3(3, NPCType.ROBIN, new NormalWood(1000) /*1000 Wood*/, new Coin(25000), false);


    private int missionNumber;
    private NPCType npcType;
    private TradeItem questItem;
    private TradeItem rewardItem;
    private boolean isActive;

    NPCQuest(int missionNumber, NPCType npcType, TradeItem questItem, TradeItem rewardItem, boolean isActive) {
        this.missionNumber = missionNumber;
        this.npcType = npcType;
        this.questItem = questItem;
        this.rewardItem = rewardItem;
        this.isActive = isActive;
    }

    public void activateQuest() {
        isActive = true;
    }
}