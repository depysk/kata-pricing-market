import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.domain.Product;
import com.practice.kata.pricing.market.service.Discount;
import com.practice.kata.pricing.market.service.PricingBuilder;
import com.practice.kata.pricing.market.service.discount.GetBackAmountOnCertainPurchaseAmountDiscount;
import com.practice.kata.pricing.market.service.discount.GetPercentageOffOnNthProductDiscount;
import com.practice.kata.pricing.market.service.discount.ManyForUniquePriceDiscount;
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
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(beer)
                    .withQuantity(3)
                    .withDiscount(theeFor2)
                    .build();

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
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(beer)
                    .withQuantity(4)
                    .withDiscount(theeFor2)
                    .build();

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
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(beer)
                    .withQuantity(5)
                    .withDiscount(theeFor2)
                    .build();

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
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(beer)
                    .withQuantity(3)
                    .withDiscount(get30PercentOnSecondProduct)
                    .build();

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
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(beer)
                    .withQuantity(10)
                    .withDiscount(get30PercentOnSecondProduct)
                    .build();

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("5.20"));
        }
    }

    @Nested
    @DisplayName("for GetBackAmountOnCertainPurchaseAmountDiscount class")
    class ForGetBackAmountOnCertainPurchaseAmountDiscount {

        private Discount get50On1000PurchaseAmount = GetBackAmountOnCertainPurchaseAmountDiscount.of(createAmount("1000"), createAmount("50"));

        @Test
        public void pricing_product_with_discount_rule_get_back_50_on_1000_purchase_amount_should_have_valid_arguments() {
            // GIVEN // WHEN // THEN
            assertThatThrownBy(() -> GetBackAmountOnCertainPurchaseAmountDiscount.of(createAmount("1000"), null))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid supply arguments");

            assertThatThrownBy(() -> GetBackAmountOnCertainPurchaseAmountDiscount.of(null, createAmount("50")))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Invalid supply arguments");
        }

        @Test
        public void pricing_product_with_discount_rule_get_back_50_on_1000_purchase_amount_should_sale_a_tv_of_1100_for_1050() {
            // GIVEN
            Product tv = createProduct("TV", createAmount("1100"));

            // WHEN
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(tv)
                    .withQuantity(1)
                    .withDiscount(get50On1000PurchaseAmount)
                    .build();

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("1050"));
        }

        @Test
        public void pricing_product_with_discount_rule_get_back_50_on_1000_purchase_amount_should_sale_2_tv_of_700_each_to_1350() {
            // GIVEN
            Product tv = createProduct("TV", createAmount("700"));

            // WHEN
            Amount actualAmount = PricingBuilder.newBuilder()
                    .withProduct(tv)
                    .withQuantity(2)
                    .withDiscount(get50On1000PurchaseAmount)
                    .build();

            // THEN
            assertThat(actualAmount)
                    .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                    .isEqualToComparingFieldByFieldRecursively(createAmount("1350"));
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
