package com.stardew_valley.models.animal;

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
//        return switch (this) {
//            case EGG -> "Egg";
//            case BIG_EGG -> "Big Egg";
//            case DUCK_EGG -> "Duck Egg";
//            case DUCK_FEATHER -> "Duck Feather";
//            case RABBIT_WOOL -> "Rabbit Wool";
//            case RABBIT_LEG -> "Rabbit Leg";
//            case DINOSAUR_EGG -> "Dinosaur Egg";
//            case MILK -> "Milk";
//            case GOAT_MILK -> "Goat Milk";
//            case BIG_MILK -> "Big Milk";
//            case BIG_GOAT_MILK -> "Big Goat Milk";
//            case SHEEP_WOOL -> "Sheep Wool";
//            case TRUFFLE -> "Truffle";
//        };
        switch (this) {
            case EGG:
                return "Egg";
            case BIG_EGG:
                return "Big Egg";
            case DUCK_EGG:
                return "Duck Egg";
            case DUCK_FEATHER:
                return "Duck Feather";
            case RABBIT_WOOL:
                return "Rabbit Wool";
            case RABBIT_LEG:
                return "Rabbit Leg";
            case DINOSAUR_EGG:
                return "Dinosaur Egg";
            case MILK:
                return "Milk";
            case GOAT_MILK:
                return "Goat Milk";
            case BIG_MILK:
                return "Big Milk";
            case BIG_GOAT_MILK:
                return "Big Goat Milk";
            case SHEEP_WOOL:
                return "Sheep Wool";
            case TRUFFLE:
                return "Truffle";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public String getName() {
        return toString();
    }

    public int getBasePrice() {
//        return switch (this) {
//            case EGG -> 50;
//            case BIG_EGG, DUCK_EGG -> 95;
//            case DUCK_FEATHER -> 250;
//            case RABBIT_WOOL, SHEEP_WOOL -> 340;
//            case RABBIT_LEG -> 565;
//            case DINOSAUR_EGG -> 350;
//            case MILK -> 125;
//            case GOAT_MILK -> 190;
//            case BIG_MILK -> 225;
//            case TRUFFLE -> 625;
//            case BIG_GOAT_MILK -> 345;
//        };
        switch (this) {
            case EGG:
                return 50;
            case BIG_EGG:
            case DUCK_EGG:
                return 95;
            case DUCK_FEATHER:
                return 250;
            case RABBIT_WOOL:
            case SHEEP_WOOL:
                return 340;
            case RABBIT_LEG:
                return 565;
            case DINOSAUR_EGG:
                return 350;
            case MILK:
                return 125;
            case GOAT_MILK:
                return 190;
            case BIG_MILK:
                return 225;
            case TRUFFLE:
                return 625;
            case BIG_GOAT_MILK:
                return 345;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
