import com.practice.kata.pricing.market.*;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountPricingTest {

    @Test
    public void pricing_product_with_discount_rule_3_for_2_should_sale_3_product_for_2() {
        // GIVEN
        Product beer = createProduct("Beer", createAmount("0.80"));

        // WHEN
        Discount theeFor2 = ManyForUniquePriceDiscount.of(3, createAmount("2"));
        Amount actualAmount = Pricing.INSTANCE.withDiscount(beer, 3, theeFor2);

        // THEN
        assertThat(actualAmount)
                .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                .isEqualToComparingFieldByFieldRecursively(createAmount("2"));
    }

    /** Dummy tests data **/
    private Product createProduct(String name, Amount unitAmount) {
        return Product.newBuilder()
                .withName(name)
                .withPrice(unitAmount)
                .build();
    }

    private Amount createAmount(String amount) {
        return Amount.newBuilder()
                .withValue(new BigDecimal(amount))
                .build();
    }

}
