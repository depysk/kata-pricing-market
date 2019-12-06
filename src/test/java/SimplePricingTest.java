import com.practice.kata.pricing.market.domain.Amount;
import com.practice.kata.pricing.market.service.Pricing;
import com.practice.kata.pricing.market.domain.Product;
import com.practice.kata.pricing.market.converter.WeightConverter;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SimplePricingTest {

    @Test
    public void simple_pricing_product_should_return_product_qty_multiply_with_product_price() {
        // GIVEN
        Product canOfBeans = createProduct("Can of beans", createAmount("0.65"));

        // WHEN
        Amount actualAmount = Pricing.INSTANCE.simple(canOfBeans, 4);

        // THEN
        assertThat(actualAmount)
                .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                .isEqualTo(createAmount("2.60"));

    }

    @Test
    public void another_simple_pricing_product_should_return_product_qty_multiply_with_product_price() {
        // GIVEN
        Product tomatoe = createProduct("Tomatoes", createAmount("0.20"));

        // WHEN
        Amount actualAmount = Pricing.INSTANCE.simple(tomatoe, WeightConverter.INSTANCE.toOunces(1));

        // THEN
        assertThat(actualAmount)
                .usingComparatorForType(new BigDecimalComparator(), BigDecimal.class)
                .isEqualToComparingFieldByFieldRecursively(createAmount("3.20"));

    }

    /** Dummy tests data **/
    private Product createProduct(String name, Amount amount) {
        return Product.newBuilder()
                .withName(name)
                .withPrice(amount)
                .build();
    }

    private Amount createAmount(String amount) {
        return Amount.newBuilder()
                .withValue(new BigDecimal(amount))
                .build();
    }
}
