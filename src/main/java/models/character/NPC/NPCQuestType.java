package models.character.NPC;

//import models.Plants.Plant;

import models.cooking.*;
import models.crafting.BeeHouse;
import models.crafting.DeluxeScarecrow;
import models.crafting.IridiumSprinkler;
import models.Plants.Plant;
import models.ingredients.*;

public enum NPCQuestType {
    SEBASTIAN_1(1, NPCType.SEBASTIAN, new IronBar(50) /*50 Iron*/, new Diamond(50)),
    SEBASTIAN_2(2, NPCType.SEBASTIAN, new PumpkinPie(1) /*1 PumpkinPie*/, new Coin(5000)),
    SEBASTIAN_3(3, NPCType.SEBASTIAN, new Stone(150) /*150 Stone*/, new Quartz(50)),
    ABIGAIL_1(4, NPCType.ABIGAIL, new GoldBar(1) /*1 GoldBar*/, new FriendshipLevel(1)),
    ABIGAIL_2(5, NPCType.ABIGAIL, new Pumpkin(1) /*1 Pumpkin*/, new Coin(500)),
    ABIGAIL_3(6, NPCType.ABIGAIL, new Wheat(50) /*50 Wheat*/, new IridiumSprinkler(1)),
    HARVEY_1(7, NPCType.HARVEY, new Plant(12) /*12 AnyPlant*/, new Coin(750)),
    HARVEY_2(8, NPCType.HARVEY, new Salmon(1) /*1 SalmonFish*/, new FriendshipLevel(1)),
    HARVEY_3(9, NPCType.HARVEY, new WineBottle(1) /*1 WineBottle*/, new Salad(5)),
    LEAH_1(10, NPCType.LEAH, new HardWood(10) /*10 HardWood*/, new Coin(500)),
    LEAH_2(11, NPCType.LEAH, new Salmon(1) /*1 SalmonFish*/, new SalmonDinner(1)),
    LEAH_3(12, NPCType.LEAH, new NormalWood(200) /*200 Wood*/, new DeluxeScarecrow(3)),
    ROBIN_1(13, NPCType.ROBIN, new NormalWood(80) /*80 Wood*/, new Coin(1000)),
    ROBIN_2(14, NPCType.ROBIN, new IronBar(10) /*10 IronBar*/, new BeeHouse(3)),
    ROBIN_3(15, NPCType.ROBIN, new NormalWood(1000) /*1000 Wood*/, new Coin(25000));


    private int missionNumber;
    private NPCType npcType;
    private TradeItem questItem;
    private TradeItem rewardItem;

    NPCQuestType(int missionNumber, NPCType npcType, TradeItem questItem, TradeItem rewardItem) {
        this.missionNumber = missionNumber;
        this.npcType = npcType;
        this.questItem = questItem;
        this.rewardItem = rewardItem;
    }

    public int getMissionNumber() {
        return missionNumber;
    }
}