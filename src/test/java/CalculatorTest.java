import com.devcouse.Calc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    
    private final Calc calc = new Calc();

    @Test
    @DisplayName("실행 성공")
    public void test_Empty() {

    }

    @Test
    @DisplayName("3 == 3")
    public void test_Integer() {
        assertThat(calc.run("3")).isEqualTo(3);
    }

    @Test
    @DisplayName("3+5 == 8")
    public void test_Plus() {
        assertThat(calc.run("3+5")).isEqualTo(8);
    }

    @Test
    @DisplayName("5-3 == 2")
    public void test_Minus() {
        assertThat(calc.run("5-3")).isEqualTo(2);
    }

    @Test
    @DisplayName("5+3-2 == 6")
    public void test_PlusMinus() {
        assertThat(calc.run("5+3-2")).isEqualTo(6);
    }

    @Test
    @DisplayName("5*5 == 25")
    public void test_Multiple() {
        assertThat(calc.run("5*5")).isEqualTo(25);
    }

    @Test
    @DisplayName("6/3 == 2")
    public void test_Division() {
        assertThat(calc.run("6/3")).isEqualTo(2);
    }

    @Test
    @DisplayName("3*8/2 = 12")
    public void test_MultipleDivision() {
        assertThat(calc.run("3*8/2")).isEqualTo(12);
    }

    @Test
    @DisplayName("3+8*2-5+10/2 == 19")
    public void test_FourBasicOpe() {
        assertThat(calc.run("3+8*2-5+10/2")).isEqualTo(19);
    }

    @Test
    @DisplayName("3*(10-5) == 15")
    public void test_Paren() {
        assertThat(calc.run("3*(10-5)")).isEqualTo(15);
    }

    @Test
    @DisplayName("(3 + (4 / 2) - (3 * 2) / 2) == 2")
    public void test_Paren2() {
        assertThat(calc.run("(3 + (4 / 2) - (3 * 2) / 2)")).isEqualTo(2);
    }

    @Test
    @DisplayName("공백 테스트: 3     *(10     - 5) == 15")
    public void test_Blank() {
        assertThat(calc.run("3     *(10     - 5)")).isEqualTo(15);
    }

    @Test
    @DisplayName("최종 테스트: ((3 + 5) * 5 + -10) * 10 / 5 == 60")
    public void test_Final() {
        int rs = calc.run("((3 + 5) * 5 + -10) * 10 / 5");
        assertThat(rs).isEqualTo(60);
    }

    @Test
    @DisplayName("1 + 1 == 2")
    void t1() {
        assertThat(calc.run("1 + 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("2 + 1 == 3")
    void t2() {
        assertThat(calc.run("2 + 1")).isEqualTo(3);
    }

    @Test
    @DisplayName("2 + 2 == 4")
    void t3() {
        assertThat(calc.run("2 + 2")).isEqualTo(4);
    }

    @Test
    @DisplayName("1000 + 280 == 1280")
    void t4() {
        assertThat(calc.run("1000 + 280")).isEqualTo(1280);
    }

    @Test
    @DisplayName("2 - 1 == 1")
    void t5() {
        assertThat(calc.run("2 - 1")).isEqualTo(1);
    }

    @Test
    @DisplayName("3 - 1 == 2")
    void t6() {
        assertThat(calc.run("3 - 1")).isEqualTo(2);
    }

    @Test
    @DisplayName("100 - 20 == 80")
    void t7() {
        assertThat(calc.run("100 - 20")).isEqualTo(80);
    }

    @Test
    @DisplayName("10 + 20 + 30 == 60")
    void t8() {
        assertThat(calc.run("10 + 20 + 30")).isEqualTo(60);
    }

    @Test
    @DisplayName("10 - 20 + 30 == 20")
    void t9() {
        assertThat(calc.run("10 - 20 + 30")).isEqualTo(20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 == -20")
    void t10() {
        assertThat(calc.run("10 - 10 - 10 - 10")).isEqualTo(-20);
    }

    @Test
    @DisplayName("10 - 10 - 10 - 10 + 10 + 10 - 10 == -10")
    void t11() {
        assertThat(calc.run("10 - 10 - 10 - 10 + 10 + 10 - 10")).isEqualTo(-10);
    }

    @Test
    @DisplayName("10 * 10 == 100")
    void t12() {
        assertThat(calc.run("10 * 10")).isEqualTo(100);
    }

    @Test
    @DisplayName("10 * -10 == -100")
    void t13() {
        assertThat(calc.run("10 * -10")).isEqualTo(-100);
    }

    @Test
    @DisplayName("10 * 10 * 10 == 1000")
    void t14() {
        assertThat(calc.run("10 * 10 * 10")).isEqualTo(1000);
    }

    @Test
    @DisplayName("10 + 5 * 2 == 20")
    void t15() {
        assertThat(calc.run("10 + 5 * 2")).isEqualTo(20);
    }

    @Test
    @DisplayName("20 + 10 + 5 * 2 == 40")
    void t16() {
        assertThat(calc.run("20 + 10 + 5 * 2")).isEqualTo(40);
    }

    @Test
    @DisplayName("10 * 20 + 10 + 5 * 2 == 220")
    void t17() {
        assertThat(calc.run("10 * 20 + 10 + 5 * 2")).isEqualTo(220);
    }

    @Test
    @DisplayName("(10 + 20) == 30")
    void t18() {
        assertThat(calc.run("(10 + 20)")).isEqualTo(30);
    }

    @Test
    @DisplayName("((10 + 20)) == 30")
    void t19() {
        assertThat(calc.run("((10 + 20))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(((10 + 20))) == 30")
    void t20() {
        assertThat(calc.run("(((10 + 20)))")).isEqualTo(30);
    }

    @Test
    @DisplayName("(20 + 20) + 20 == 60")
    void t21() {
        assertThat(calc.run("(20 + 20) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("((20 + 20)) + 20 == 60")
    void t22() {
        assertThat(calc.run("((20 + 20)) + 20")).isEqualTo(60);
    }

    @Test
    @DisplayName("(10 + 20) * 3 == 90")
    void t23() {
        assertThat(calc.run("(10 + 20) * 3")).isEqualTo(90);
    }

    @Test
    @DisplayName("10 + (10 + 5) == 25")
    void t24() {
        assertThat(calc.run("10 + (10 + 5)")).isEqualTo(25);
    }

    @Test
    @DisplayName("-(10 + 5) == -15")
    void t25() {
        assertThat(calc.run("-(10 + 5)")).isEqualTo(-15);
    }

    @Test
    @DisplayName("-(8 + 2) * -(7 + 3) + 5 == 105")
    void t26() {
        assertThat(calc.run("-(8 + 2) * -(7 + 3) + 5")).isEqualTo(105);
    }

    @Test
    @DisplayName("5 - (1 + 5) == -1")
    void t27() {
        assertThat(calc.run("5 - (1 + 5)")).isEqualTo(-1);
    }

    @Test
    @DisplayName("3 * 1 + (1 - (4 * 1 - (1 - 1))) == 0")
    void t28() {
        assertThat(calc.run("3 * 1 + (1 - (4 * 1 - (1 - 1)))")).isEqualTo(0);
    }

    @Test
    @DisplayName("-10 + 5 == 5")
    void t29() {
        assertThat(calc.run("-10 + 5")).isEqualTo(-5);
    }

    @Test
    @DisplayName("(1 + 2 == Error")
    void t30() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () ->calc.run("(1 + 2"));

        assertEquals(exception.getMessage(), "괄호가 올바르지 않습니다: (1+2");
    }
}
