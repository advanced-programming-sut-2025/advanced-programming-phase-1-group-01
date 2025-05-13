package models.farming;

public class Tree extends Plant {
    private final TreeInfo info;
    private TreeState state;

    public Tree(TreeInfo info) {
        this.info = info;
        state = TreeState.HEALTHY;
    }

    public TreeState getState() {
        return state;
    }

    public void broke() {
        state = TreeState.BROKEN;
    }

    public void burn() {
        state = TreeState.BURNT;
    }

    public void repair() {
        state = TreeState.HEALTHY;
    }

    @Override
    public void grow() { // this method should be called every day
        int[] growthStages = info.getStages();
        if (isFullyGrown()) return;
        int currentLevelDays = growthStages[growthLevel];

        if (daysInCurrentLevel >= currentLevelDays) {
            growthLevel++;
        }

        daysInCurrentLevel++;
    }

    public boolean isFullyGrown() {
        return growthLevel >= info.getStages().length;
    }
}
