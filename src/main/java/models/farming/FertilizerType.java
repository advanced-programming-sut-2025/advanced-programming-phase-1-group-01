package models.farming;

public enum FertilizerType {
    BASIC, QUALITY, DELUXE;

    @Override
    public String toString() {
        return switch (this) {
            case BASIC -> "Basic";
            case QUALITY -> "Quality";
            case DELUXE -> "Deluxe";
        };
    }
}
