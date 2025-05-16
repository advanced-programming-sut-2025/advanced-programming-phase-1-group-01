package models.relations;

import models.character.player.Player;
import models.enums.Gender;

public class Marriage extends Relationship {
    private Player husband;
    private Player wife;
    private int lastRelation;


    public Marriage(Player p1, Player p2) {
        if (p1.getGender() == p2.getGender()) return;

        else if (p1.getGender() == Gender.MALE) {
            husband = p1;
            wife = p2;
        }

        else {
            husband = p2;
            wife = p1;
        }
        this.lastRelation = 0;
    }

    public Player getHusband() {
        return husband;
    }

    public Player getWife() {
        return wife;
    }

    public Player getPartner(Player p) {
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
