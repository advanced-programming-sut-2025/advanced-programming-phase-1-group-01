package models.relations;

// level 1 : 100 xp
// level 2 : 200 xp
// ...

public abstract class Relationship {
    protected int level;
    protected int xp; // xp is different for each level

    private static final int MAX_XP = 100;

    public int getXp() {
        return xp;
    }

    public void increaseXp(int amount) {
        if (amount > 0) xp += amount;
        if (xp >= MAX_XP) xp = MAX_XP;
    }

    public void incrementLevel() {
        // here some stuff should be checked
        level++;
        xp = 0;
    }

    public int getLevel() {
        return level;
    }

    public void decrementLevel() {
        // here some stuff should be checked
        level--;
        xp = 100;
    }
}
