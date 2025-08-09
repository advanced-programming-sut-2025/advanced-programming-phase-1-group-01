package com.stardew_valley.models.relations;

import com.stardew_valley.models.character.player.User;
import com.stardew_valley.models.enums.Gender;

public class Marriage extends Relationship {
    private User husband;
    private User wife;
    private int lastRelation;

    public Marriage(User p1, User p2) throws GaysMarriageException {
        if (p1.getGender() == p2.getGender()) {
            throw new GaysMarriageException();
        } else if (p1.getGender() == Gender.MALE) {
            husband = p1;
            wife = p2;
        } else {
            husband = p2;
            wife = p1;
        }
        this.lastRelation = 0;
    }

    public User getHusband() {
        return husband;
    }

    public User getWife() {
        return wife;
    }

    public User getPartner(User p) {
        if (p != husband && p != wife) {
            return null;
        }

        if (p == husband) {
            return wife;
        }
        return husband;
    }

    public int getLastRelation() {
        return lastRelation;
    }

    public void setLastRelation(int lastRelation) {
        this.lastRelation = lastRelation;
    }
}
