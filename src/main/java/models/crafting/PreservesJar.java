package models.crafting;

import models.Item;
import models.enums.Emoji;

import java.util.List;

public class PreservesJar extends CraftingDevice {

    @Override
    public String getName() {
        return "PreservesJar";
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
        return Emoji.PRESERVE_JAR.getSymbol();
    }
}
