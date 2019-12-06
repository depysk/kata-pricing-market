package com.practice.kata.pricing.market.service;

import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.domain.Product;

@FunctionalInterface
public interface Discount {
    Amount apply(Product product, int quantity);
}
