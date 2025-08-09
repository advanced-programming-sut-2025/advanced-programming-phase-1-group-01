package com.stardew_valley.models.character.NPC;

import com.stardew_valley.models.character.player.User;

public class NPCQuest {
    private final NPCQuestType questType;
    private boolean isActive;
    private boolean isCompleted;
    private User owner;

    public NPCQuest(NPCQuestType questType) {
        this.questType = questType;
        this.isActive = false;
        this.isCompleted = false;
    }

    public void activateQuest() {
        this.isActive = true;
    }

    public void completeQuest() {
        this.isCompleted = true;
    }

    public NPCQuestType getQuestType() {
        return questType;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
