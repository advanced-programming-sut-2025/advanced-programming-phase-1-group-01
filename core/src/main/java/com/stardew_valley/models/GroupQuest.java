package com.stardew_valley.models;

import com.stardew_valley.models.enums.GroupQuestType;

import java.util.ArrayList;
import java.util.List;

public class GroupQuest {
    private final GroupQuestType type;
    private final List<String> playerUsername = new ArrayList<>();
    private int doneAmount = 0;
    private boolean isFinished = false;
    private boolean isStarted = false;

    public GroupQuest(GroupQuestType type) {
        this.type = type;
    }

    public GroupQuestType getType() {
        return type;
    }

    public void addPlayer(String username) {
        playerUsername.add(username);
    }

    public boolean isInList(String username) {
        return playerUsername.contains(username);
    }

    public void addUser(String username) {
        if(!isInList(username)) playerUsername.add(username);
    }

    public void addAmount(int amount) {
        doneAmount += amount;
        if (doneAmount > type.getRequiredAmount()) {
            isFinished = true;
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

}
