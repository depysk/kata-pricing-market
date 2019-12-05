package com.practice.kata.pricing.market;

public enum WeightConverter {
    INSTANCE;
    public int toOunces(int pounds) {
        return pounds * 16;
    }
}
