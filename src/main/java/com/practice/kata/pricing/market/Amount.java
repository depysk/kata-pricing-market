package com.practice.kata.pricing.market;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {
    private BigDecimal value;

    private Amount(Builder builder) {
        value = builder.value;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static final class Builder {
        private BigDecimal value;

        private Builder() {
        }

        public Builder withValue(BigDecimal val) {
            value = val;
            return this;
        }

        public Amount build() {
            if(value == null) {
                throw new RuntimeException("Please provide an amount");
            }
            return new Amount(this);
        }
    }
}
