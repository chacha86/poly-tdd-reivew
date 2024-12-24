import static enums.Operator.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import util.Calculator;
import util.Evaluator;
import util.Parser;

public class CalculatorTest {

    @Test
    @DisplayName("테스트 함수 실행")
    public void test1() {
        System.out.println("test1");
    }

    @Test
    @DisplayName("plus 함수 구현")
    public void plusTest() {
        Calculator calculator = new Calculator();
        calculator.plus(1, 2);
    }

    @Test
    @DisplayName("plus함수 1 + 2 테스트")
    public void plusTest2() {
        Calculator calculator = new Calculator();
        int result = calculator.plus(1, 2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("plus 함수 4 + 6 테스트")
    public void plusTest3() {
        Calculator calculator = new Calculator();
        int result = calculator.plus(4, 6);

        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("plus 함수 1 + 4 테스트")
    public void plusTest4() {
        Calculator calculator = new Calculator();
        int result = calculator.plus(1, 4);

        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("minus 함수 2 - 1 테스트")
    public void minusTest() {
        Calculator calculator = new Calculator();
        int result = calculator.minus(2, 1);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("minus 함수 4 - 1 테스트")
    public void minusTest2() {
        Calculator calculator = new Calculator();
        int result = calculator.minus(4, 1);

        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("multiply 함수 2 * 1 테스트")
    public void multiplyTest() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(2, 1);

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("multiply 함수 4 * 1 테스트")
    public void multiplyTest2() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(4, 1);

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("divide 함수 2 / 1 테스트")
    public void divideTest() {
        Calculator calculator = new Calculator();
        int result = calculator.divide(2, 1);

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("divide 함수 4 / 3 테스트")
    public void divideTest2() {
        Calculator calculator = new Calculator();
        int result = calculator.divide(4, 3);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("(3+5) -> [ (, 3, +, 5, ) ]")
    public void parsingTest() {
        Parser parser = new Parser();
        List<String> tokens = parser.parse("(3+5)");

        assertThat(tokens).containsExactly("(", "3", "+", "5", ")");
    }

    @Test
    @DisplayName("(3 + 5) -> [ (, 3, +, 5, ) ]")
    public void parsingTest2() {
        Parser parser = new Parser();
        List<String> tokens = parser.parse("(3 + 5)");

        assertThat(tokens).containsExactly("(", "3", "+", "5", ")");
    }

    @Test
    @DisplayName("(-3 + 5) -> [ (, -3, +, 5, ) ]")
    public void parsingTest3() {
        Parser parser = new Parser();
        List<String> tokens = parser.parse("(-3 + 5)");

        assertThat(tokens).containsExactly("(", "-3", "+", "5", ")");
    }

    @Test
    @DisplayName("(3 + -5) -> [ (, 3, +, -5, ) ]")
    public void parsingTest4() {
        Parser parser = new Parser();
        List<String> tokens = parser.parse("(3 + -5)");

        assertThat(tokens).containsExactly("(", "3", "+", "-5", ")");
    }

    @Test
    @DisplayName("-(5) == -5")
    public void calcSignTest() {
        Evaluator evaluator = new Evaluator();
        int result = evaluator.calcSign(5, MINUS.getString());

        assertThat(result).isEqualTo(-5);
    }

    @Test
    @DisplayName("find '(' in [ (, (, 3, +, -5, ), *, 5, +, -10, ) * 10 ]")
    public void findLeftBracketTest() {
        List<String> tokens = Arrays.asList(
            new String[] {"(", "(", "3", "+", "-5", ")", "*", "5", "+", "-10", ")", "*", "10"}
        );
        Evaluator evaluator = new Evaluator();
        int leftIndex = evaluator.findLeftBracket(tokens);

        assertThat(leftIndex).isEqualTo(0);
    }

    @Test
    @DisplayName("find ')' in [ (, (, 3, +, -5, ), *, 5, +, -10, ) * 10 ]")
    public void findRightBracketTest() {
        List<String> tokens = Arrays.asList(
            new String[] {"(", "(", "3", "+", "-5", ")", "*", "5", "+", "-10", ")", "*", "10"}
        );
        Evaluator evaluator = new Evaluator();
        int rightIndex = evaluator.findRightBracket(tokens, 0);

        assertThat(rightIndex).isEqualTo(10);
    }

    @Test
    @DisplayName("3 + 5 * 8 == 3 + 40")
    public void evaluatePriorityTest() {
        List<String> tokens = Arrays.asList(
            new String[] {"3", "+", "5", "*", "8"}
        );
        Evaluator evaluator = new Evaluator();
        List<String> expected = Arrays.asList(
            new String[] {"3", "+", "40"}
        );
        List<String> result = evaluator.evaluatePriority(tokens, MUL.getString(), DIV.getString());

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("[ (, 3, +, -5, ), *, 5, +, -10 ] -> [ -2, *, 5, +, -10 ]")
    public void addResToLeftTest() {
        List<String> tokens = Arrays.asList(
            new String[] {"(", "3", "+", "-5", ")", "*", "5", "+", "-10"}
        );
        Evaluator evaluator = new Evaluator();
        List<String> expected = Arrays.asList(
            new String[] {"-2", "*", "5", "+", "-10"}
        );
        List<String> result = evaluator.addResToLeft(tokens, -2, 4);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("[ 3, +, -5, *, (5, +, -10) ] -> [ 3, +, -5, *, -5 ]")
    public void addResToRightTest() {
        List<String> tokens = Arrays.asList(
            new String[] {"3", "+", "-5", "*", "(", "5", "+", "-10", ")"}
        );
        Evaluator evaluator = new Evaluator();
        List<String> expected = Arrays.asList(
            new String[] {"3", "+", "-5", "*", "-5"}
        );
        List<String> result = evaluator.addResToRight(tokens, -5, 4);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("[ -, (, 3, +, -5, ), *, 5, +, -10 ] -> [ -2, *, 5, +, -10 ]")
    public void addResToCenterTest() {
        List<String> tokens = Arrays.asList(
            new String[] {"-", "(", "3", "+", "-5", ")", "*", "5", "+", "-10"}
        );
        Evaluator evaluator = new Evaluator();
        List<String> expected = Arrays.asList(
            new String[] {"2", "*", "5", "+", "-10"}
        );
        List<String> result = evaluator.addResToCenter(
            tokens, evaluator.calcSign(-2, MINUS.getString()), 0, 5
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("(3 + 5) == 8")
    public void calcTest() {
        Calculator calculator = new Calculator();
        int result = calculator.calc("(3 + 5)");

        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("(3 + 5) * 5 == 40")
    public void calcTest2() {
        Calculator calculator = new Calculator();
        int result = calculator.calc("(3 + 5) * 5");

        assertThat(result).isEqualTo(40);
    }

    @Test
    @DisplayName("(3 + 5) * 5 + 10 == 50")
    public void calcTest3() {
        Calculator calculator = new Calculator();
        int result = calculator.calc("(3 + 5) * 5 + 10");

        assertThat(result).isEqualTo(50);
    }

    @Test
    @DisplayName("(3 + 5) * 5 + -10 == 30")
    public void calcTest4() {
        Calculator calculator = new Calculator();
        int result = calculator.calc("(3 + 5) * 5 + -10");

        assertThat(result).isEqualTo(30);
    }

    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 == 300")
    public void calcTest5() {
        Calculator calculator = new Calculator();
        int result = calculator.calc("((3 + 5) * 5 + -10) * 10");

        assertThat(result).isEqualTo(300);
    }

    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 / 5 == 60")
    public void calcTest6() {
        Calculator calculator = new Calculator();
        int result = calculator.calc("((3 + 5) * 5 + -10) * 10 / 5");

        assertThat(result).isEqualTo(60);
    }

    /**
     * ---------------------------------예제---------------------------------
     **/

    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("1000 + 280")).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("2 - 1")).isEqualTo(1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("3 - 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("100 - 20")).isEqualTo(80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 + 20 + 30")).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 - 20 + 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void t10() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 - 10 - 10 - 10")).isEqualTo(-20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t11() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void t12() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 * 10")).isEqualTo(100);
    }

    @Test
    @DisplayName("10 * -10 == -100")
    void t13() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 * -10")).isEqualTo(-100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t14() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 * 10 * 10")).isEqualTo(1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t15() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 + 5 * 2")).isEqualTo(20);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t16() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("20 + 10 + 5 * 2")).isEqualTo(40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t17() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t18() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("(10 + 20)")).isEqualTo(30);
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    void t19() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("((10 + 20))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t20() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("(((10 + 20)))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t21() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("(20 + 20) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void t22() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("((20 + 20)) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t23() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("(10 + 20) * 3")).isEqualTo(90);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void t24() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("10 + (10 + 5)")).isEqualTo(25);
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void t25() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("-(10 + 5)")).isEqualTo(-15);
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void t26() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("-(8 + 2) * -(7 + 3) + 5")).isEqualTo(105);
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void t27() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("5 - (1 + 5)")).isEqualTo(-1);
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void t28() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calc("3 * 1 + (1 - (4 * 1 - (1 - 1)))")).isEqualTo(0);
    }
}
