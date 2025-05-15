package models.tool.enums;

public enum TrashCanType {
    PRIMARY(0), COPPER(15), IRON(30), GOLD(45), IRIDIUM(60);

    private final int returnValuePercentage;
    // no energy use for trash can

    TrashCanType(int returnValuePercentage) {
        this.returnValuePercentage = returnValuePercentage;
    }

    public int getReturnValuePercentage() {
        return returnValuePercentage;
    }
}
