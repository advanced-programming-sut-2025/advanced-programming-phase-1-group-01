package models.animal;

import models.Size;

public enum AnimalHouseType {

    COOP(4, new Size(3, 3)),
    BIG_COOP(8, new Size(4, 4)),
    DELUXE_COOP(12, new Size(5, 5)),
    BARN(4, new Size(3, 3)),
    BIG_BARN(8, new Size(4, 4)),
    DELUXE_BARN(12, new Size(5, 5));

    private final int capacity;
    private final Size size;

    AnimalHouseType(int capacity, Size size) {
        this.capacity = capacity;
        this.size = size;
    }

    public AnimalHouseType getBaseType() {
        return switch (this) {
            case DELUXE_BARN, BIG_BARN -> BARN;
            case DELUXE_COOP, BIG_COOP -> COOP;
            default -> this;
        };
    }

    public int getCapacity() {
        return capacity;
    }

    public Size getSize() {
        return size;
    }
}
