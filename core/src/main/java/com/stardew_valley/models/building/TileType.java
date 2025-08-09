package com.stardew_valley.models.building;

public enum TileType {
    GROUND("🟩"), RIVER("〰️"), MINE("⛰️"), GREENHOUSE("🍀"), COTTAGE("🟫"), WALL("🧱")
    , FENCE("⛓"),SHIPPING_BIN("🚽"), SHOP("🚽");
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

    public static TileType getTileTypeByNumber(int num) {
        switch (num) {
            case 1: return TileType.GROUND;
            case 2: return TileType.RIVER;
            case 3: return TileType.MINE;
            case 4: return TileType.GREENHOUSE;
            case 5: return TileType.COTTAGE;
            case 6: return TileType.WALL;
            case 7: return TileType.FENCE;
            case 8: return TileType.SHIPPING_BIN;
            default: return null;
        }
    }

    public static int getNumberFromTileType(TileType type) {
        switch (type) {
            case GROUND: return 1;
            case RIVER: return 2;
            case MINE: return 3;
            case GREENHOUSE: return 4;
            case COTTAGE: return 5;
            case WALL: return 6;
            case FENCE: return 7;
            case SHIPPING_BIN: return 8;
            default: return -1;
        }
    }

}
