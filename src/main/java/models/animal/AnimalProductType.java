package models.animal;

public enum AnimalProductType {
    EGG,
    BIG_EGG,
    DUCK_EGG,
    DUCK_FEATHER,
    RABBIT_WOOL,
    RABBIT_LEG,
    DINOSAUR_EGG,
    MILK,
    BIG_MILK,
    GOAT_MILK,
    BIG_GOAT_MILK,
    SHEEP_WOOL,
    TRUFFLE;


    @Override
    public String toString() {
        return switch (this) {
            case EGG -> "Egg";
            case BIG_EGG -> "Big Egg";
            case DUCK_EGG -> "Duck Egg";
            case DUCK_FEATHER -> "Duck Feather";
            case RABBIT_WOOL -> "Rabbit Wool";
            case RABBIT_LEG -> "Rabbit Leg";
            case DINOSAUR_EGG -> "Dinosaur Egg";
            case MILK -> "Milk";
            case GOAT_MILK -> "Goat Milk";
            case BIG_MILK -> "Big Milk";
            case BIG_GOAT_MILK -> "Big Goat Milk";
            case SHEEP_WOOL -> "Sheep Wool";
            case TRUFFLE -> "Truffle";
        };
    }

    public String getName() {
        return toString();
    }

    public int getBasePrice() {
        return switch (this) {
            case EGG -> 50;
            case BIG_EGG, DUCK_EGG -> 95;
            case DUCK_FEATHER -> 250;
            case RABBIT_WOOL, SHEEP_WOOL -> 340;
            case RABBIT_LEG -> 565;
            case DINOSAUR_EGG -> 350;
            case MILK -> 125;
            case GOAT_MILK -> 190;
            case BIG_MILK -> 225;
            case TRUFFLE -> 625;
            case BIG_GOAT_MILK -> 345;
        };
    }
}