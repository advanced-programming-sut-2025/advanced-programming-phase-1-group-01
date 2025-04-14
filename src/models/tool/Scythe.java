package models.tool;

public class Scythe extends Tool {
    private int useEnergy;

    private static final int INITIAL_SCYTHE_USE_ENERGY = 2;

    public Scythe() {
        useEnergy = INITIAL_SCYTHE_USE_ENERGY;
    }
}
