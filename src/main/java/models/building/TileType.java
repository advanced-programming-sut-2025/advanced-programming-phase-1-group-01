package models.building;

public enum TileType {
    GROUND("🟩"), RIVER("〰️"), MINE("⛰️"), GREENHOUSE("🍀"), COTTAGE("🟫"), WALL("🧱"),
    SALE_BUCKET("🚽"), FENCE("⛓");

    private final String symbol;

    TileType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static String getFormattedGuidance() {
        TileType[] values = TileType.values();
        StringBuilder output = new StringBuilder();
        for (TileType value : values) {
            String formattedLine = String.format("%-" + 11 + "s", value.name());
            output.append(formattedLine).append(" : ").append(value.getSymbol()).append("\n");
        }
        return output.toString();
    }
}
