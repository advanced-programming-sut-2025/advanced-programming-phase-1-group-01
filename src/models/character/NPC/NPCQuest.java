package models.character.NPC;

public enum NPCQuest {
    SEBASTIAN_1(1, NPCType.SEBASTIAN, new TradeItem(null, 50) /*50 Iron*/, new TradeItem(null, 2) /*2 Diamond*/),
    SEBASTIAN_2(2, NPCType.SEBASTIAN, new TradeItem(null, 1) /*1 PumpkinPie*/, new TradeItem(null, 5000) /*GoldCoin*/),
    SEBASTIAN_3(3, NPCType.SEBASTIAN, new TradeItem(null, 150) /*150 Stone*/, new TradeItem(null, 50) /*50 Quartz*/),
    ABIGAIL_1(1, NPCType.ABIGAIL, new TradeItem(null, 1) /*1 GoldBar*/, new TradeItem(null, 1) /*1 FriendshipLevel*/),
    ABIGAIL_2(2, NPCType.ABIGAIL, new TradeItem(null, 1) /*1 Pumpkin*/, new TradeItem(null, 500) /*500 GoldCoins*/),
    ABIGAIL_3(3, NPCType.ABIGAIL, new TradeItem(null, 50) /*50 Wheat*/, new TradeItem(null, 1) /*1 IridiumAutomaticSprinkler*/),
    HARVEY_1(1, NPCType.HARVEY, new TradeItem(null, 12) /*12 AnyPlant*/, new TradeItem(null, 750) /*750 GoldCoin*/),
    HARVEY_2(2, NPCType.HARVEY, new TradeItem(null, 1) /*1 SalmonFish*/, new TradeItem(null, 1) /*1 FriendshipLevel*/),
    HARVEY_3(3, NPCType.HARVEY, new TradeItem(null, 1) /*1 WineBottle*/, new TradeItem(null, 5) /*5 Salad*/),
    LEAH_1(1, NPCType.LEAH, new TradeItem(null, 10) /*10 HardWood*/, new TradeItem(null, 500) /*500 GoldCoins*/),
    LEAH_2(2, NPCType.LEAH, new TradeItem(null, 1) /*1 SalmonFish*/, new TradeItem(null, 1) /*1 DinnerSalmonRecipe*/),
    LEAH_3(3, NPCType.LEAH, new TradeItem(null, 200) /*200 Wood*/, new TradeItem(null, 3) /*3 DeluxeScarecrow*/),
    ROBIN_1(1, NPCType.ROBIN, new TradeItem(null, 80) /*80 Wood*/, new TradeItem(null, 1000) /*1000 GoldCoin*/),
    ROBIN_2(2, NPCType.ROBIN, new TradeItem(null, 10) /*10 IronBar*/, new TradeItem(null, 3) /*3 BeeHouse*/),
    ROBIN_3(3, NPCType.ROBIN, new TradeItem(null, 1000) /*1000 Wood*/, new TradeItem(null, 25000) /*25000 GoldCoins*/);

    private int missionNumber;
    private NPCType npcType;
    private TradeItem questItem;
    private TradeItem rewardItem;

    NPCQuest(int missionNumber, NPCType npcType, TradeItem questItem, TradeItem rewardItem) {
        this.missionNumber = missionNumber;
        this.npcType = npcType;
        this.questItem = questItem;
        this.rewardItem = rewardItem;
    }
}