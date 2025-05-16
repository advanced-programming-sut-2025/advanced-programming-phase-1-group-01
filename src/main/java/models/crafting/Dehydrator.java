package models.crafting;

import models.Item;
import models.enums.Emoji;

import java.util.List;

public class Dehydrator extends CraftingDevice {

    @Override
    public String getName() {
        return "Dehydrator";
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
        return Emoji.DEHYDRATOR.getSymbol();
    }
}
