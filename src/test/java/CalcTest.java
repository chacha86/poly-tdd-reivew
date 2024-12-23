import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalcTest {

    @Test
    @DisplayName("3 + 5")
    public void t1() {
        int rs=Calc.run("3 + 5");
        Assertions.assertThat(rs).isEqualTo(8);
    }

    @Test
    @DisplayName("(3 + 5)")
    public void t2() {
        int rs=Calc.run("(3 + 5)");
        Assertions.assertThat(rs).isEqualTo(8);
    }

    @Test
    @DisplayName("(3 + 5) * 5")
    public void t3() {
        int rs=Calc.run("(3 + 5) * 5");
        Assertions.assertThat(rs).isEqualTo(40);
    }

    @Test
    @DisplayName("((3 + 5) * 5 + -10)")
    public void t4() {
        int rs=Calc.run("((3 + 5) * 5 + -10)");
        Assertions.assertThat(rs).isEqualTo(30);
    }

    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10")
    public void t5() {
        int rs=Calc.run("((3 + 5) * 5 + -10) * 10");
        Assertions.assertThat(rs).isEqualTo(300);
    }

    @Test
    @DisplayName("((3 + 5) * 5 + -10) * 10 / 5")
    public void t6() {
        int rs=Calc.run("((3 + 5) * 5 + -10) * 10 / 5");
        Assertions.assertThat(rs).isEqualTo(60);
    }
}
