package models.enums;

import models.building.TileObject;

import java.util.Random;

public enum StoneType implements TileObject {
    FIRST("💎"), SECOND("🪨"), THIRD("🗿"), FOURTH("🌋");


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
