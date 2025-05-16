package models.crafting;

import models.Item;
import models.enums.Emoji;

import java.util.List;

public class OilMaker extends CraftingDevice {

    @Override
    public String getName() {
        return "Oil Maker";
    }

    @Override
    public boolean canProcess(Item item) {
        return false;
    }

    @Override
    public void insertItem(List<Item> items) {

    }

    @Override
    public Item collectProduct() {
        return null;
    }

    @Override
    public int getRequiredTurns() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return Emoji.OIL_MAKER.getSymbol();
    }
}
