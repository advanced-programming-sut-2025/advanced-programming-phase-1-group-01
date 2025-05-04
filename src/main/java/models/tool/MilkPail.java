package models.tool;

public class MilkPail extends Tool {
    private static final int INITIAL_MILK_PAIL_USE_ENERGY = 4;

    public MilkPail() {
        baseEnergyCost = INITIAL_MILK_PAIL_USE_ENERGY;
    }

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
