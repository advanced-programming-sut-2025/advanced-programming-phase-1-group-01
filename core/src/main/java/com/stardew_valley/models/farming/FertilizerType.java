package com.stardew_valley.models.farming;

public enum FertilizerType {
    BASIC, QUALITY, DELUXE;

    @Override
    public String toString() {
//        return switch (this) {
//            case BASIC -> "Basic";
//            case QUALITY -> "Quality";
//            case DELUXE -> "Deluxe";
//        };
        switch (this) {
            case BASIC:
                return "Basic";
            case QUALITY:
                return "Quality";
            case DELUXE:
                return "Deluxe";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
