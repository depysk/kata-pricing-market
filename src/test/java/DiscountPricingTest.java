import com.practice.kata.pricing.market.*;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiscountPricingTest {

    @Nested
    @DisplayName("for ManyForUniquePriceDiscount class")
    class ForManyForUniquePriceDiscount {

        private Discount theeFor2 = ManyForUniquePriceDiscount.of(3, createAmount("2"));

        @Test
        public void pricing_product_with_discount_rule_3_for_2_should_have_valid_arguments() {
            // GIVEN // WHEN // THEN
            assertThatThrownBy(() -> ManyForUniquePriceDiscount.of(0, createAmount("2")))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid supply arguments");

            assertThatThrownBy(() -> ManyForUniquePriceDiscount.of(2, null))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid supply arguments");
        }

        @Test
        public void pricing_product_with_discount_rule_3_for_2_should_sale_3_product_for_2() {
            // GIVEN
            Product beer = createProduct("Beer", createAmount("0.80"));

            // WHEN
            Amount actualAmount = Pricing.INSTANCE.withDiscount(beer, 3, theeFor2);

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("2"));
        }

        @Test
        public void pricing_product_with_discount_rule_3_for_2_should_sale_4_product_for_2_dot_65() {
            // GIVEN
            Product beer = createProduct("Beer", createAmount("0.80"));

            // WHEN
            Amount actualAmount = Pricing.INSTANCE.withDiscount(beer, 4, theeFor2);

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("2.80"));
        }

        @Test
        public void pricing_product_with_discount_rule_3_for_2_should_sale_5_product_for_3_dot_30() {
            // GIVEN
            Product beer = createProduct("Beer", createAmount("0.80"));

            // WHEN
            Amount actualAmount = Pricing.INSTANCE.withDiscount(beer, 5, theeFor2);

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("3.60"));
        }

    }

    @Nested
    @DisplayName("for GetPercentageOffOnNthProductDiscount class")
    class ForGetPercentageOffOnNthProductDiscount {

        private Discount get30PercentOnSecondProduct = GetPercentageOffOnNthProductDiscount.of(30, 2);

        @Test
        public void pricing_product_with_discount_rule_30_on_2nd_product_should_have_valid_arguments() {
            // GIVEN // WHEN // THEN
            assertThatThrownBy(() -> GetPercentageOffOnNthProductDiscount.of(0, 2))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid supply arguments");

            assertThatThrownBy(() -> GetPercentageOffOnNthProductDiscount.of(2, 0))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid supply arguments");
        }

        @Test
        public void pricing_product_with_discount_rule_30_on_2nd_product_should_should_sale_3_product_for_1_dot_84() {
            // GIVEN
            Product beer = createProduct("Beer", createAmount("0.80"));

            // WHEN
            Amount actualAmount = Pricing.INSTANCE.withDiscount(beer, 3, get30PercentOnSecondProduct);

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("1.84"));
        }

        @Test
        public void pricing_product_with_discount_rule_30_on_2nd_product_should_should_sale_10_product_for_5_dot_20() {
            // GIVEN
            Product beer = createProduct("Beer", createAmount("0.80"));

            // WHEN
            Amount actualAmount = Pricing.INSTANCE.withDiscount(beer, 10, get30PercentOnSecondProduct);

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("5.20"));
        }
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
