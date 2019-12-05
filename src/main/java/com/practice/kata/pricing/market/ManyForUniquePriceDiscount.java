package com.practice.kata.pricing.market;

import java.math.BigDecimal;

public class ManyForUniquePriceDiscount implements Discount {

    private int discountQty;
    private Amount discountPrice;

    private ManyForUniquePriceDiscount(int discountQty, Amount discountPrice) {
        this.discountQty = discountQty;
        this.discountPrice = discountPrice;
    }

    public static ManyForUniquePriceDiscount of(int discountQty, Amount discountPrice) {
        return new ManyForUniquePriceDiscount(discountQty, discountPrice);
    }

    @Override
    public Amount apply(Product product, int quantity) {

        if(quantity == discountQty) {
            return discountPrice;
        }

        int div = quantity / discountQty;
        int rest = quantity % discountQty;

        BigDecimal amountValue = discountPrice.getValue()
                .multiply(BigDecimal.valueOf(div))
                .add(product.getPrice().getValue().multiply(BigDecimal.valueOf(rest)));

        return Amount.newBuilder().withValue(amountValue).build();
    }
}