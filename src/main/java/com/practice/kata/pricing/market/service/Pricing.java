package com.practice.kata.pricing.market.service;

import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.domain.Product;

import java.math.BigDecimal;

enum Pricing {
    INSTANCE;
    public Amount simple(Product product, int quantity) {
        BigDecimal value = product.getPrice().getValue().multiply(new BigDecimal(quantity));
        return Amount.newBuilder().withValue(value).build();
    }

    public Amount withDiscount(Product product, int quantity, Discount discount) {
        return discount.apply(product, quantity);
    }
}
