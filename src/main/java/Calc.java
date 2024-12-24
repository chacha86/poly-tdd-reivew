import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static int run(String exp) {
        exp = exp.replaceAll("\\s", "");
        exp = exp.replaceAll("(?<![0-9)])-\\(", "-1*(");

        if (exp.contains("(")) {
            int start = exp.lastIndexOf('(');
            int end = exp.indexOf(')', start);

            String innerExp = exp.substring(start + 1, end);
            int result = run(innerExp);

            return run(exp.substring(0, start) + result + exp.substring(end + 1));
        }

        return compute(exp);
    }

    public static int compute(String exp) {
        List<Integer> nums = new ArrayList<>();
        int i = 0;
        int sign = 1;
        int result = 0;

        while (i < exp.length()) {
            char ch = exp.charAt(i);

            if (ch == '+' || ch == '-') {
                sign = (ch == '+') ? 1 : -1;
                i++;
            }

            else if (ch == '*' || ch == '/') {
                char op = ch;
                int sign2 = 1;
                int num = 0;

                i++;

                if (i < exp.length() && exp.charAt(i) == '-') {
                    sign2 = -1;
                    i++;
                }

                while(i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    num = num * 10 + (exp.charAt(i) - '0');
                    i++;
                }

                num *= sign2;

                if (op == '*') {
                    nums.set(nums.size() - 1, nums.get(nums.size() - 1) * num);
                }
                else {
                    nums.set(nums.size() - 1, nums.get(nums.size() - 1) / num);
                }
            }

            else if (Character.isDigit(ch)) {
                int num = 0;

                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    num = num * 10 + (exp.charAt(i) - '0');
                    i++;
                }

                num *= sign;
                nums.add(num);

                sign = 1;
            }

            else {
                i++;
            }
        }

        for (int num : nums) {
            result += num;
        }

        return result;
    }
}
