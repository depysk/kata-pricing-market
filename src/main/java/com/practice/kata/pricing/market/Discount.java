package com.practice.kata.pricing.market;

@FunctionalInterface
public interface Discount {
    Amount apply(Product product, int quantity);
}
