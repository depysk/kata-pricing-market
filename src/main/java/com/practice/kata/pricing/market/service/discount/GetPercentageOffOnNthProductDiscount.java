package com.practice.kata.pricing.market.service.discount;

import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.domain.Product;
import com.practice.kata.pricing.market.service.Discount;

import java.math.BigDecimal;

public class GetPercentageOffOnNthProductDiscount implements Discount {

    final private int discountPercentage;
    final private int nthProduct;
    private static final int PERCENTAGE = 100;

    private GetPercentageOffOnNthProductDiscount(int discountPercentage, int nthProduct) {
        this.discountPercentage = discountPercentage;
        this.nthProduct = nthProduct;
    }

    public static GetPercentageOffOnNthProductDiscount of(int discountPercentage, int nthProduct) {
        if(discountPercentage <= 0 || nthProduct <= 0) {
            throw new RuntimeException("Invalid supply arguments");
        }
        return new GetPercentageOffOnNthProductDiscount(discountPercentage, nthProduct);
    }

    @Override
    public Amount apply(Product product, int quantity) {

        int div = quantity / nthProduct;

        BigDecimal notSolded = product.getPrice().getValue().multiply(BigDecimal.valueOf(quantity - div));
        BigDecimal solded = product.getPrice().getValue().multiply(BigDecimal.valueOf(div))
                .multiply(BigDecimal.valueOf(discountPercentage)).divide(BigDecimal.valueOf(PERCENTAGE), BigDecimal.ROUND_HALF_UP);

        return Amount.newBuilder().withValue(notSolded.add(solded)).build();
    }
}
