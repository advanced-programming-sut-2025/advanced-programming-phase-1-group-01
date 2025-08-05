package com.stardew_valley.models.relations;

public class GaysMarriageException extends RuntimeException {
    public GaysMarriageException(String message) {
        super(message);
    }

    public GaysMarriageException() {
        super("Gays Marriage isn't allowed!");
    }
}
