package com.practice.kata.pricing.market;

import java.util.Objects;

public class Product {
    private String name;
    private Amount price;

    private Product(Builder builder) {
        name = builder.name;
        price = builder.price;
    }

    public String getName() {
        return name;
    }

    public Amount getPrice() {
        return price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public static final class Builder {
        private String name;
        private Amount price;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withPrice(Amount val) {
            price = val;
            return this;
        }

        public Product build() {
            if(name == null || name.isEmpty()) {
                throw new RuntimeException("Please provide Product name");
            }
            if(price == null) {
                throw new RuntimeException("Please provide Product price");
            }
            return new Product(this);
        }
    }
}
