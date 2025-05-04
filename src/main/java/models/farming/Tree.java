package models.farming;

public class Tree extends Plant {
    private TreeState state;

    public Tree() {
        state = TreeState.HEALTHY;
    }

    public TreeState getState() {
        return state;
    }

    public void broke() {
        state = TreeState.BROKEN;
    }

    public void repair() {
        state = TreeState.HEALTHY;
    }
}
