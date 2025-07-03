package com.stardew_valley.models.foraging;

import com.stardew_valley.models.Item;
import com.stardew_valley.models.farming.TreeSource;

public class ForagingTree extends Foraging implements Item, TreeSource {
    private final ForagingTreeInfo info;

    public ForagingTree(ForagingTreeInfo info) {
        this.info = info;
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    public ForagingTreeInfo getInfo() {
        return info;
    }

    @Override
    public String getSymbol() {
        return info.getSymbol();
    }
}
