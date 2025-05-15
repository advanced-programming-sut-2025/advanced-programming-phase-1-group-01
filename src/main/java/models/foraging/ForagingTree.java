package models.foraging;

import models.Item;
import models.building.TileObject;
import models.farming.TreeSource;

public class ForagingTree extends Foraging implements Item, TreeSource {
    private final ForagingTreeInfo info;

    public ForagingTree(ForagingTreeInfo info) {
        this.info = info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    public ForagingTreeInfo getInfo() {
        return info;
    }

    @Override
    public String getSymbol() {
        return info.getSymbol();
    }
}
