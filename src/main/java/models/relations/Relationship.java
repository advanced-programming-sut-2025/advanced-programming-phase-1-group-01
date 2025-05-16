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
        if (level == 2) {
            xp = getMaxXp();
        }
        // here some stuff should be checked
        else {
            level++;
            xp = 0;
        }
    }

    public int getLevel() {
        return level;
    }

    public void decrementLevel() {
        // here some stuff should be checked
        level--;
        xp = getMaxXp() ;
    }

    public void flower() {
        level = 3;
        xp = 0;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
