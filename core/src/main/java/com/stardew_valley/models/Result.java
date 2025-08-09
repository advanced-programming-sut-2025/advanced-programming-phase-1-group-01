package com.stardew_valley.models;

public record Result(boolean success, String message) {

    @Override
    public String toString() {
        return "[%b] %s".formatted(success, message);
    }
}
