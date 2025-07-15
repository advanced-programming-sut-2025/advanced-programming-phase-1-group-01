package com.stardew_valley.models.enums;

public enum Gender {
    MALE("🧑"), FEMALE("👩");

    private final String symbol;

    Gender(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
