package models.enums;

import models.TileContent;

import java.util.Random;

public enum Stones implements TileContent {
    FIRST("💎"), SECOND("🪨"), THIRD("🗿"), FOURTH("🌋");


    private final String symbol;
    private static final Random RANDOM = new Random();

    Stones(String symbol) {
        this.symbol = symbol;
    }

    public static Stones randomStone() {
        Stones[] values = Stones.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getSymbol() {
        return symbol;
    }
}
