package models.tool;

public class Scythe extends Tool {
    private static final int INITIAL_SCYTHE_USE_ENERGY = 2;

    public Scythe() {
        baseEnergyCost = INITIAL_SCYTHE_USE_ENERGY;
    }

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
