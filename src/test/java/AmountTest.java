import com.practice.kata.pricing.market.Amount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AmountTest {

    @Test
    public void an_amount_should_have_a_bigdecimal_value() {
        //GIVEN
        Amount amount = createAmount("5.50");

        //WHEN //THEN
        assertThat(amount.getValue()).isInstanceOf(BigDecimal.class).isEqualTo("5.50");
    }

    @Test
    public void an_amount_without_value_should_throw_an_exception() {
        //GIVEN //WHEN //THEN
        assertThatThrownBy(() -> Amount.newBuilder().withValue(null).build())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Please provide an amount");
    }

    @Test
    public void two_amount_with_same_value_should_be_equals() {
        //GIVEN
        Amount amount1 = createAmount("2.50");
        Amount amount2 = createAmount("2.50");

        //WHEN //THEN
        assertThat(amount1).isEqualTo(amount2);
        assertThat(amount1).hasSameHashCodeAs(amount2);
    }

    /** Dummy tests data **/
    private Amount createAmount(String amount) {
        return Amount.newBuilder()
                .withValue(new BigDecimal(amount))
                .build();
    }
}
