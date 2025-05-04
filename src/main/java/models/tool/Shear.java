package models.tool;

public class Shear extends Tool {
    private static final int INITIAL_SHEAR_USE_ENERGY = 4;

    public Shear() {
        baseEnergyCost = INITIAL_SHEAR_USE_ENERGY;
    }

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
