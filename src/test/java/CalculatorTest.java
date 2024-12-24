import org.assertj.core.api.Assertions;
import org.example.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    @DisplayName("테스트 함수 실행")
    public void test1() {
        System.out.println("test1");
    }


    @Test
    @DisplayName("plus 함수 구현")
    public void t2() {
        Calculator calculator = new Calculator();
        calculator.plus(1, 2);
    }

    @Test
    @DisplayName("plus 함수 1 + 2 테스트")
    public void t3() {
        Calculator calculator = new Calculator();
        int rst = calculator.plus(1, 2);
        Assertions.assertThat(rst).isEqualTo(3);
    }

    @Test
    @DisplayName("plus 함수 4 + 6 테스트")
    public void t4() {
        Calculator calculator = new Calculator();
        int rst = calculator.plus(4, 6);
        Assertions.assertThat(rst).isEqualTo(10);
    }

    @Test
    @DisplayName("plus 함수 1 + 4 테스트")
    public void t5() {
        Calculator calculator = new Calculator();
        int rst = calculator.plus(1, 4);
        Assertions.assertThat(rst).isEqualTo(5);
    }

}