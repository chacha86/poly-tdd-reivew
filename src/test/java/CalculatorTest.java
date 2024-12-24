import org.assertj.core.api.SoftAssertions;
import org.calc.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    SoftAssertions softly;
    Calculator calc;

    @BeforeEach
    void setUp() {
        softly = new SoftAssertions();
        calc = new Calculator();
    }

    @Nested
    @DisplayName("사칙연산")
    class Basic_calculation {
        @Test
        @DisplayName("덧셈을 계산할 수 있다")
        void calculate_add() {
            softly.assertThat(calc.add(3, 5)).isEqualTo(8);
            softly.assertThat(calc.add(1, 2)).isEqualTo(3);
            softly.assertThat(calc.add(8, 3)).isEqualTo(11);
            softly.assertThat(calc.add(4, 4)).isEqualTo(8);
            softly.assertThat(calc.add(9, 7)).isEqualTo(16);
            softly.assertThat(calc.add(3, 2)).isEqualTo(5);
            softly.assertAll();
        }

        @Test
        @DisplayName("뺄셈을 계산할 수 있다.")
        void calculate_subtract() {
            softly.assertThat(calc.subtract(3, 5)).isEqualTo(-2);
            softly.assertThat(calc.subtract(1, 2)).isEqualTo(-1);
            softly.assertThat(calc.subtract(8, 3)).isEqualTo(5);
            softly.assertThat(calc.subtract(4, 4)).isEqualTo(0);
            softly.assertThat(calc.subtract(9, 7)).isEqualTo(2);
            softly.assertThat(calc.subtract(3, 2)).isEqualTo(1);
            softly.assertAll();
        }

        @Test
        @DisplayName("곱셈을 계산할 수 있다")
        void calculate_multiply() {
            softly.assertThat(calc.multiply(3, 5)).isEqualTo(15);
            softly.assertThat(calc.multiply(1, 2)).isEqualTo(2);
            softly.assertThat(calc.multiply(8, 3)).isEqualTo(24);
            softly.assertThat(calc.multiply(4, 4)).isEqualTo(16);
            softly.assertThat(calc.multiply(9, 7)).isEqualTo(63);
            softly.assertThat(calc.multiply(3, 2)).isEqualTo(6);
            softly.assertAll();
        }

        @Test
        @DisplayName("나눗셈을 계산할 수 있다")
        void calculate_divide() {
            softly.assertThat(calc.divide(3, 5)).isEqualTo(0);
            softly.assertThat(calc.divide(1, 2)).isEqualTo(0);
            softly.assertThat(calc.divide(8, 3)).isEqualTo(2);
            softly.assertThat(calc.divide(4, 4)).isEqualTo(1);
            softly.assertThat(calc.divide(9, 7)).isEqualTo(1);
            softly.assertThat(calc.divide(3, 2)).isEqualTo(1);
            softly.assertAll();
        }
    }

    @Test
    @DisplayName("계산식을 계산할 수 있다")
    void calculate_expression_add() {
        softly.assertThat(calc.calculateExpression("(3 + 5)")).isEqualTo(8);
        softly.assertThat(calc.calculateExpression("(3+5)")).isEqualTo(8);
        softly.assertThat(calc.calculateExpression("7 + 6")).isEqualTo(13);
        softly.assertThat(calc.calculateExpression("(2 * 8)")).isEqualTo(16);
        softly.assertThat(calc.calculateExpression("2* 8")).isEqualTo(16);
        softly.assertThat(calc.calculateExpression("5 * 6")).isEqualTo(30);
        softly.assertThat(calc.calculateExpression("5*6")).isEqualTo(30);
        softly.assertThat(calc.calculateExpression("(10 / 3)")).isEqualTo(3);
        softly.assertThat(calc.calculateExpression("10 /3")).isEqualTo(3);
        softly.assertThat(calc.calculateExpression("9 / 2")).isEqualTo(4);
        softly.assertThat(calc.calculateExpression("10 +33")).isEqualTo(43);
        softly.assertAll();
    }

    @Test
    @DisplayName("숫자를 0으로 나누면 예외가 발생한다")
    void divided_by_zero() {
        softly.assertThatThrownBy(() -> {
            calc.calculateExpression("(33/0)");
        }).isInstanceOf(ArithmeticException.class).hasMessage("0으로 나눌 수 없습니다");
        softly.assertAll();
    }

    @Test
    @DisplayName("음수가 있는 계산식을 계산할 수 있다")
    void calculate_negative_number() {
        softly.assertThat(calc.calculateExpression("3 + -7")).isEqualTo(-4);
        softly.assertThat(calc.calculateExpression("(-12 * -3)")).isEqualTo(36);
        softly.assertThat(calc.calculateExpression("(12 / -2)")).isEqualTo(-6);
        softly.assertThat(calc.calculateExpression("-5 - -2")).isEqualTo(-3);
        softly.assertAll();
    }

    @Test
    @DisplayName("곱셈/나눔셈을 먼저 계산한다 : 항이 여러 개인 계산식을 계산할 수 있다")
    void polynomial_calculate() {
        softly.assertThat(calc.calculateExpression("2 + 3 + 5")).isEqualTo(10);
        softly.assertThat(calc.calculateExpression("-5 -2 + 3")).isEqualTo(-4);
        softly.assertThat(calc.calculateExpression("3 * 2 - 3")).isEqualTo(3);
        softly.assertThat(calc.calculateExpression("2 - 4 * 5")).isEqualTo(-18);
        softly.assertThat(calc.calculateExpression("2 * 4 - 3 * 8 / 2 + 5")).isEqualTo(1);
        softly.assertAll();
    }

    @Test
    @DisplayName("괄호 안의 계산식을 먼저 계산한다 : 혼합 계산식을 계산할 수 있다")
    void calculate_combined_operations() {
        softly.assertThat(calc.calculateExpression("(3 + 5) + 4")).isEqualTo(12);
        softly.assertThat(calc.calculateExpression("((3 + 5) * 5 + -10) * 10")).isEqualTo(300);
        softly.assertThat(calc.calculateExpression("2 +3 * (-4 + 7)")).isEqualTo(11);
        softly.assertThat(calc.calculateExpression("6 + (5 * 9) / 3")).isEqualTo(21);
        softly.assertAll();
    }

    @Nested
    @DisplayName("계산 테스트 케이스")
    class Calculate_expression_testcase {
        @Test
        @DisplayName("1 + 1 == 2")
        void t1() {
            assertThat(calc.calculateExpression("1 + 1")).isEqualTo(2);
        }

        @Test
        @DisplayName("2 + 1 == 3")
        void t2() {
            assertThat(calc.calculateExpression("2 + 1")).isEqualTo(3);
        }

        @Test
        @DisplayName("2 + 2 == 4")
        void t3() {
            assertThat(calc.calculateExpression("2 + 2")).isEqualTo(4);
        }

        @Test
        @DisplayName("1000 + 280 == 1280")
        void t4() {
            assertThat(calc.calculateExpression("1000 + 280")).isEqualTo(1280);
        }

        @Test
        @DisplayName("2 - 1 == 1")
        void t5() {
            assertThat(calc.calculateExpression("2 - 1")).isEqualTo(1);
        }

        @Test
        @DisplayName("3 - 1 == 2")
        void t6() {
            assertThat(calc.calculateExpression("3 - 1")).isEqualTo(2);
        }

        @Test
        @DisplayName("100 - 20 == 80")
        void t7() {
            assertThat(calc.calculateExpression("100 - 20")).isEqualTo(80);
        }

        @Test
        @DisplayName("10 + 20 + 30 == 60")
        void t8() {
            assertThat(calc.calculateExpression("10 + 20 + 30")).isEqualTo(60);
        }

        @Test
        @DisplayName("10 - 20 + 30 == 20")
        void t9() {
            assertThat(calc.calculateExpression("10 - 20 + 30")).isEqualTo(20);
        }

        @Test
        @DisplayName("10 - 10 - 10 - 10 == -20")
        void t10() {
            assertThat(calc.calculateExpression("10 - 10 - 10 - 10")).isEqualTo(-20);
        }

        @Test
        @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
        void t11() {
            assertThat(calc.calculateExpression("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
        }

        @Test
        @DisplayName("10 * 10 == 100")
        void t12() {
            assertThat(calc.calculateExpression("10 * 10")).isEqualTo(100);
        }

        @Test
        @DisplayName("10 * -10 == -100")
        void t13() {
            assertThat(calc.calculateExpression("10 * -10")).isEqualTo(-100);
        }

        @Test
        @DisplayName("10 * 10 * 10 == 1000")
        void t14() {
            assertThat(calc.calculateExpression("10 * 10 * 10")).isEqualTo(1000);
        }

        @Test
        @DisplayName("10 + 5 * 2 == 20")
        void t15() {
            assertThat(calc.calculateExpression("10 + 5 * 2")).isEqualTo(20);
        }

        @Test
        @DisplayName("20 + 10 + 5 * 2 == 40")
        void t16() {
            assertThat(calc.calculateExpression("20 + 10 + 5 * 2")).isEqualTo(40);
        }

        @Test
        @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
        void t17() {
            assertThat(calc.calculateExpression("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
        }

        @Test
        @DisplayName("(10 + 20) == 30")
        void t18() {
            assertThat(calc.calculateExpression("(10 + 20)")).isEqualTo(30);
        }

        @Test
        @DisplayName("((10 + 20)) == 30")
        void t19() {
            assertThat(calc.calculateExpression("((10 + 20))")).isEqualTo(30);
        }

        @Test
        @DisplayName("(((10 + 20))) == 30")
        void t20() {
            assertThat(calc.calculateExpression("(((10 + 20)))")).isEqualTo(30);
        }

        @Test
        @DisplayName("(20 + 20) + 20 == 60")
        void t21() {
            assertThat(calc.calculateExpression("(20 + 20) + 20")).isEqualTo(60);
        }

        @Test
        @DisplayName("((20 + 20)) + 20 == 60")
        void t22() {
            assertThat(calc.calculateExpression("((20 + 20)) + 20")).isEqualTo(60);
        }

        @Test
        @DisplayName("(10 + 20) * 3 == 90")
        void t23() {
            assertThat(calc.calculateExpression("(10 + 20) * 3")).isEqualTo(90);
        }

        @Test
        @DisplayName("10 + (10 + 5) == 25")
        void t24() {
            assertThat(calc.calculateExpression("10 + (10 + 5)")).isEqualTo(25);
        }

        @Test
        @DisplayName("-(10 + 5) == -15")
        void t25() {
            assertThat(calc.calculateExpression("-(10 + 5)")).isEqualTo(-15);
        }

        @Test
        @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
        void t26() {
            assertThat(calc.calculateExpression("-(8 + 2) * -(7 + 3) + 5")).isEqualTo(105);
        }

        @Test
        @DisplayName("5 - (1 + 5) == -1")
        void t27() {
            assertThat(calc.calculateExpression("5 - (1 + 5)")).isEqualTo(-1);
        }

        @Test
        @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
        void t28() {
            assertThat(calc.calculateExpression("3 * 1 + (1 - (4 * 1 - (1 - 1)))")).isEqualTo(0);
        }
    }
}
