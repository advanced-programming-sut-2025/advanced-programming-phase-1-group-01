package models.tool.enums;

public enum HoeType {
    PRIMARY(5), COPPER(4), IRON(3), GOLD(2), IRIDIUM(1),;

    private int useEnergy;

    HoeType(int useEnergy) {
        this.useEnergy = useEnergy;
    }

    public int getUseEnergy() {
        return useEnergy;
    }

    public void setUseEnergy(int useEnergy) {
        this.useEnergy = useEnergy;
    }
}
