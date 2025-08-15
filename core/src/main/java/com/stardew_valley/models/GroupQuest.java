package com.stardew_valley.models;

import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.GroupQuestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupQuest {
    private final GroupQuestType type;
    private final Map<String, Float> playerUsernames = new HashMap<>();
    private float doneAmount = 0;
    private boolean isFinished = false;
    private boolean isStarted = false;
    private int hourCounter = 0;

    public GroupQuest(GroupQuestType type) {
        this.type = type;
    }

    public GroupQuestType getType() {
        return type;
    }

    public void addPlayer(String username) {
        if (!playerUsernames.containsKey(username)) {
            playerUsernames.put(username, 0f);
        }
    }

    public boolean isInList(String username) {
        return playerUsernames.containsKey(username);
    }

    public void addAmount(float amount, String username) {
        doneAmount += amount;
        playerUsernames.put(username, playerUsernames.getOrDefault(username, 0f) + amount);
        if (doneAmount > type.getRequiredAmount()) {
            isFinished = true;
            isStarted = false;
            doneAmount = 0;
            hourCounter = 0;
            Repository.getRepo().getCurrentUser().getPlayer().increaseCoins(type.getAward());
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted() {
        isStarted = true;
    }

    public void addToGroup(String username) {
        addPlayer(username);
        if (playerUsernames.size() >= type.getGroupSize()) {
            isStarted = true;
        }
    }

    public void advanceHour() {
        hourCounter++;
        if (hourCounter > type.getDuration()) {
            isFinished = true;
            isStarted = false;
            doneAmount = 0;
            hourCounter = 0;
        }
    }

    public Map<String, Float> getPlayerUsernames() {
        return playerUsernames;
    }

    public float getDoneAmount() {
        return doneAmount;
    }

    public int getHourCounter() {
        return hourCounter;
    }

}
