package models.character.NPC;

import models.character.player.Player;

public class NPCQuest {
    private final NPCQuestType questType;
    private boolean isActive;
    private boolean isCompleted;
    private Player owner;

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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
