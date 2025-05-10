package models.relations;

import models.character.player.Player;
import models.enums.Gender;

public class Marriage extends Relationship {
    private Player husband;
    private Player wife;

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
}
