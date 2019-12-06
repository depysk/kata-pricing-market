package com.practice.kata.pricing.market.service;

import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.domain.Product;

public class PricingBuilder {

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Product product;
        private int quantity;
        private Discount discount;

        private Builder() {
        }

        public Builder withProduct(Product val) {
            product = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Builder withDiscount(Discount val) {
            discount = val;
            return this;
        }

        public Amount build() {
            if(discount == null) {
                return Pricing.INSTANCE.simple(product, quantity);
            }
            return Pricing.INSTANCE.withDiscount(product, quantity, discount);
        }
    }
}
