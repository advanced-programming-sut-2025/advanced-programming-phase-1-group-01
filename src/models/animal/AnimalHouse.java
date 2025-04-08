package models.animal;

public enum AnimalHouse {
    COOP(4), BIG_COOP(8), DELUXE_COOP(12), BARN(4), BIG_BARN(8), DELUXE_BARN(12);

    private int capacity;

    AnimalHouse(int capacity) {
        this.capacity = capacity;
    }
}
