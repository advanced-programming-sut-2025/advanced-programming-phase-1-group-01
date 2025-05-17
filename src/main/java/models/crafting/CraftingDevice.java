package models.crafting;

import models.Item;
import models.building.TileObject;

import java.util.List;

public abstract class CraftingDevice implements Item, TileObject {
    protected int x;
    protected int y;
    protected boolean isWorking;


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public int getPrice() {
        return -1;
    }

    public abstract boolean canProcess(Item item);
    public abstract void insertItem(List<Item> items);
    public abstract Item collectProduct();
    public abstract int getRequiredTurns();

}

