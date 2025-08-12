package com.stardew_valley.models.enums;

public enum GroupQuestType {
    WALKING("Walk 100,000 pixels", 100000, 24, 5000),
    ANIMAL("Add 10 new animals", 10, 24, 7000),
    MARRIAGE("Have 2 marriages", 2, 72, 15000),
    QUEST("Complete 5 NPC missions", 5, 48, 10000),
    CRAFT("Build 10 machines", 10, 48, 12000),
    PLOW("Plow 200 tiles", 200, 72, 6000),
    REACT("Perform 30 reactions", 30, 120, 9000),
    MONEY("Earn 5,000 coins", 5000, 96, 5000);

    private final String description;
    private final int requiredAmount;
    private final int duration;
    private final int award;

    GroupQuestType(String description, int requiredAmount, int duration, int award) {
        this.description = description;
        this.requiredAmount = requiredAmount;
        this.duration = duration;
        this.award = award;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public int getDuration() {
        return duration;
    }

    public int getAward() {
        return award;
    }
}
