package com.practice.kata.pricing.market.converter;

public enum WeightConverter {
    INSTANCE;
    public int toOunces(int pounds) {
        return pounds * 16;
    }
}
