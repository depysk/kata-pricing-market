package com.practice.kata.pricing.market.service.discount;

import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.domain.Product;
import com.practice.kata.pricing.market.service.Discount;

import java.math.BigDecimal;

public class GetBackAmountOnCertainPurchaseAmountDiscount implements Discount {

    private final Amount floorAmount;
    private final Amount refundAmount;

    private GetBackAmountOnCertainPurchaseAmountDiscount(Amount floorAmount, Amount refundAmount) {
        this.floorAmount = floorAmount;
        this.refundAmount = refundAmount;
    }

    public static GetBackAmountOnCertainPurchaseAmountDiscount of(Amount floorAmount, Amount refundAmount) {
        if(floorAmount == null || refundAmount == null) {
            throw new RuntimeException("Invalid supply arguments");
        }
        return new GetBackAmountOnCertainPurchaseAmountDiscount(floorAmount, refundAmount);
    }

    @Override
    public Amount apply(Product product, int quantity) {

        BigDecimal amountValue = product.getPrice().getValue().multiply(BigDecimal.valueOf(quantity));

        if(amountValue.compareTo(floorAmount.getValue()) >= 0) {
            amountValue = amountValue.subtract(refundAmount.getValue());
        }

        return Amount.newBuilder().withValue(amountValue).build();
    }
}
