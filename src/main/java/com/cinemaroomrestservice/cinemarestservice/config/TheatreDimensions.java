package com.cinemaroomrestservice.cinemarestservice.config;

public enum TheatreDimensions {
    ROWS(9),
    COLUMNS(9);

    private final int value;

    TheatreDimensions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
