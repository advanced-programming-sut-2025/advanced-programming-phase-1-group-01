package models.building;

public enum TileType {
    GROUND("🟩"), RIVER("〰️"), MINE("⛰️"), GREENHOUSE("🍀"), COTTAGE("🟫"), WALL("🧱");

    private final String symbol;

    TileType(String symbol) {
        this.symbol = symbol;
    }
}
