package models.character.player;

public class Ability {
    private AbilityType abilityType;
    private int level;
    private int xp;

    Ability(AbilityType abilityType, int level, int xp) {
        this.abilityType = abilityType;
        this.level = level;
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isFull() {
        return true; // should be overwritten
    }
}
