package models.tool.enums;

public enum AxeType {
    PRIMARY(5), COPPER(4), IRON(3), GOLD(2), IRIDIUM(1),;

    private int useEnergy;

    AxeType(int useEnergy) {
        this.useEnergy = useEnergy;
    }

    public int getUseEnergy() {
        return useEnergy;
    }

    public void setUseEnergy(int useEnergy) {
        this.useEnergy = useEnergy;
    }
}
