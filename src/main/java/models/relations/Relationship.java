package models.relations;

// level 1 : 100 xp
// level 2 : 200 xp
// ...

public abstract class Relationship {
    protected int level;
    protected int xp; // xp is different for each level

    public int getXp() {
        return xp;
    }

    public void increaseXp(int amount) {
        for (int i = 0; i < amount; i++) {
            xp++;
            if (xp >= getMaxXp()) {
                incrementLevel();
            }
        }
    }

    public void decreaseXp(int amount) {
        for (int i = 0; i < amount; i++) {
            xp--;
            if (xp <= 0) {
                decrementLevel();
            }
        }
    }

    public int getMaxXp() {
        return (level + 1) * 100;
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
        xp = getMaxXp() ;
    }
}
