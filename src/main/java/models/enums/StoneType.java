package models.enums;

import models.building.TileObject;

import java.util.Random;

public enum StoneType {
        FIRST("💎"), SECOND("🪨"), THIRD("🥌"), FOURTH("🌋");
//    FIRST("S1"), SECOND("S2"), THIRD("S3"), FOURTH("S4");


    private final String symbol;
    private static final Random RANDOM = new Random();

    StoneType(String symbol) {
        this.symbol = symbol;
    }

    public static StoneType randomStone() {
        StoneType[] values = StoneType.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getSymbol() {
        return symbol;
    }
}
