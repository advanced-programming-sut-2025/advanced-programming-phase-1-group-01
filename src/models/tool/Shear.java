package models.tool;

public class Shear extends Tool {
    private int useEnergy;

    private static final int INITIAL_SHEAR_USE_ENERGY = 4;

    public Shear() {
        useEnergy = INITIAL_SHEAR_USE_ENERGY;
    }
}
