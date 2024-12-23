import org.example.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    @Test
    @DisplayName("덧셈")
    void runTest() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 + 2");
        assertThat(res).isEqualTo(3.0);
    }
    @Test
    @DisplayName("뺄셈")
    void runTest1() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 - 2");
        assertThat(res).isEqualTo(-1.0);
    }
    @Test
    @DisplayName("곱셈")
    void runTest2() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 * 2");
        assertThat(res).isEqualTo(2.0);
    }@Test
    @DisplayName("나눗셈")
    void runTest3() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 / 2");
        assertThat(res).isEqualTo(0.5);
    }

    @Test
    @DisplayName("여러항 계산")
    void runTest4() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 + 2 + 3");
        assertThat(res).isEqualTo(6.0);
    }

    @Test
    @DisplayName("순서 정렬이 필요한 여러항 계산")
    void runTest5() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 + 2 * 3");
        assertThat(res).isEqualTo(7.0);
    }

    @Test
    @DisplayName("괄호가 포함된 여러항 계산")
    void runTest6() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 + (-4 + 2) * 3");
        assertThat(res).isEqualTo(-5.0);
    }
    @Test
    @DisplayName("중첩된 괄호가 포함된 여러항 계산")
    void runTest7() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("1 + (-4 + (6 + 2) * 2) * 3");
        assertThat(res).isEqualTo(37.0);
    }
    @Test
    @DisplayName("최종 결과")
    void runTest8() {
        Calculator calculator = new Calculator();
        Number res = calculator.run("((3 + 5) * 5 + -10) * 10 / 5");
        assertThat(res).isEqualTo(60.0);
    }
}
