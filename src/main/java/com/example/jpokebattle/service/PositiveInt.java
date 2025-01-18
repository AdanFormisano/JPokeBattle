package com.example.jpokebattle.service;

public class PositiveInt {
    private final int value;

    public PositiveInt(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive");
        }
        this.value = value;
    }

    public int getValue() { return this.value; }
    public double doubleValue() { return this.value; }
}
