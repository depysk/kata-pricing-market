import com.practice.kata.pricing.market.WeightConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeightConverterTest {

    @Test
    public void should_convert_1_pound_to_16_ounces() {
        //GIVEN //WHEN
        int ounces = WeightConverter.INSTANCE.toOunces(1);

        //THEN
        assertThat(ounces).isEqualTo(16);
    }
}
