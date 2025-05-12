package models.farming;

public class Tree extends Plant {
    private TreeState state;

    public Tree(int amount) {
        super(amount);
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
}
