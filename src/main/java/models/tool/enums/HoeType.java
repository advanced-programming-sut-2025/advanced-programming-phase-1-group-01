package models.tool.enums;

public enum HoeType {
    PRIMARY(5), COPPER(4), IRON(3), GOLD(2), IRIDIUM(1),;

    private int energyCost;

    HoeType(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }
}
