package models.tool.enums;

public enum AxeType {
    PRIMARY(5), COPPER(4), IRON(3), GOLD(2), IRIDIUM(1),;

    private int energyCost;

    AxeType(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }
}
