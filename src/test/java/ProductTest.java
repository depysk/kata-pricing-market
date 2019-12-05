import com.practice.kata.pricing.market.Amount;
import com.practice.kata.pricing.market.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    public void a_product_should_have_a_name_a_price_and_an_unit() {
        //GIVEN
        Product banana = createProduct("Apple", createAmount("2.50"));

        //WHEN //THEN
        assertThat(banana.getName()).isEqualTo("Apple");
        assertThat(banana.getPrice()).isEqualTo(createAmount("2.50"));
    }

    @Test
    public void a_product_without_name_should_throw_an_exception() {
        //GIVEN //WHEN //THEN
        assertThatThrownBy(() -> createProduct(null, createAmount("2.50")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Please provide Product name");
    }

    @Test
    public void a_product_without_price_should_throw_an_exception() {
        //GIVEN //WHEN //THEN
        assertThatThrownBy(() -> createProduct("Apple", null))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Please provide Product price");
    }

    @Test
    public void two_products_with_same_informations_should_be_equals() {
        //GIVEN
        Product banana1 = createProduct("Apple", createAmount("7.50"));
        Product banana2 = createProduct("Apple", createAmount("7.50"));

        //WHEN //THEN
        assertThat(banana1).isEqualTo(banana2);
        assertThat(banana1).hasSameHashCodeAs(banana2);
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
