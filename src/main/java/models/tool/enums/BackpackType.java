package models.tool.enums;

public enum BackpackType {
    SMALL(12), BIG(24), DELUXE(Double.POSITIVE_INFINITY);


    private double capacity;


    public static final int INFINITE_CAPACITY = -1;

    BackpackType(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {

        return capacity;
    }
}
