package models.tool;

public class MilkPail extends Tool {
    private int useEnergy;

    private static final int INITIAL_MILK_PAIL_USE_ENERGY = 4;

    public MilkPail() {
        useEnergy = INITIAL_MILK_PAIL_USE_ENERGY;
    }

    @Override
    public void use() {

    }
}
