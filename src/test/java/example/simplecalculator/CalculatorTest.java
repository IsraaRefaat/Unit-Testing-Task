package example.simplecalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class CalculatorTest {

    Calculator calculator = new Calculator();


    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "4, 5, 9",
            "2.5, 2.5, 5.0"
    })
    void givenValidNumbers_whenAdd_thenReturnsCorrectSum(double a, double b, double expected) {
        double result = calculator.add(a, b);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "-1 , -2",
            "1, -2 ",
            "-1, 2"
    })
    void givenInvalidNumbers_whenAdd_thenThrowException(double a, double b) {
        assertThatThrownBy(() ->
                calculator.add(a, b))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Numbers must be positive");
    }



}
