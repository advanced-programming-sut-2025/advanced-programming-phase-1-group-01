package models.tool.enums;

public enum BackpackType {
    SMALL(12), BIG(24), DELUXE(BackpackType.INFINITE_CAPACITY);

    private final int capacity;

    public static final int INFINITE_CAPACITY = -1;

    BackpackType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
