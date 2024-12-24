import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator = new Calculator();
    
    @Test
    @DisplayName("더하기 1 + 2 = 3")
    public void add() {
        int result = calculator.run("1 + 2");
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("더하기 40 + 5 = 45")
    public void add2() {
        int result = calculator.run("40 + 5");
        Assertions.assertThat(result).isEqualTo(45);
    }

    @Test
    @DisplayName("빼기 3 - 1 = 2")
    public void subtract() {
        int result = calculator.run("3 - 1");
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("빼기 4 + -66 = -62")
    public void subtract2() {
        int result = calculator.run("4 + -66");
        Assertions.assertThat(result).isEqualTo(-62);
    }

    @Test
    @DisplayName("곱하기 12 * 3 = 36")
    public void multiply() {
        int result = calculator.run("12 * 3");
        Assertions.assertThat(result).isEqualTo(36);
    }

    @Test
    @DisplayName("나누기 12 / 3 = 4")
    public void divide() {
        int result = calculator.run("12 / 3");
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("사칙연산 1 + 3 * 5 = 16")
    public void basicArithmetic() {
        int result = calculator.run("1 + 3 * 5");
        Assertions.assertThat(result).isEqualTo(16);
    }

    @Test
    @DisplayName("사칙연산 16 / 2 + 70 * 3 - 100 = 118")
    public void basicArithmetic2() {
        int result = calculator.run("16 / 2 + 70 * 3 - 100");
        Assertions.assertThat(result).isEqualTo(118);
    }

    @Test
    @DisplayName("괄호 더하기 ( 1+ 2) + 3 = 6")
    public void addGroup() {
        int result = calculator.run("( 1+ 2) + 3");
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("괄호 빼기 -( 1 - 2) - 3 = -2")
    public void subtractGroup() {
        int result = calculator.run("-( 1 - 2) - 3");
        Assertions.assertThat(result).isEqualTo(-2);
    }

    @Test
    @DisplayName("괄호 곱하기 5 * (4 * 12 ) + 2 = 242")
    public void multiplyGroup() {
        int result = calculator.run("5 * (4 * 12 ) + 2");
        Assertions.assertThat(result).isEqualTo(242);
    }

    @Test
    @DisplayName("괄호 나누기 10 / (40 / 4 ) * 2 = 2")
    public void divideGroup() {
        int result = calculator.run("10 / (40 / 4 ) * 2");
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("다항식 사칙연산 ((3 + 5) * 5 + -10) * 10 / 5 = 60")
    public void polynomialArithmetic() {
        int result = calculator.run("((3 + 5) * 5 + -10) * 10 / 5");
        Assertions.assertThat(result).isEqualTo(60);

        Assertions.assertThat(calculator.run("((2 + 4) * 3 - 6) / 3")).isEqualTo(4);
        Assertions.assertThat(calculator.run("(10 / 2 + 8) * 3 - 6")).isEqualTo(33);
        Assertions.assertThat(calculator.run("((5 - 2) * 4 + 10) / 2")).isEqualTo(11);
        Assertions.assertThat(calculator.run("(3 + (6 / 2) * 3 - 8) * 2")).isEqualTo(8);
        Assertions.assertThat(calculator.run("(5 + (4 * 3) - 6) / 2")).isEqualTo(5);
        Assertions.assertThat(calculator.run("((12 / (3 + 1)) * 3 + 6 - 8)")).isEqualTo(7);
    }

    @Test
    @DisplayName("다항식 사칙연산 (15 / (3 + 2)) * (4 + 6) = 30")
    public void polynomialArithmetic2() {
        Assertions.assertThat(calculator.run("(15 / (3 + 2)) * (4 + 6)")).isEqualTo(30);
        Assertions.assertThat(calculator.run("(((8 - 3) * 2) + (4 * 3)) / 2")).isEqualTo(11);
        Assertions.assertThat(calculator.run("((6 * 3) / 2 + (10 - 4)) * 2")).isEqualTo(30);
    }

}
