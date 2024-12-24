import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calc {
    public int run(String exp){
        int start = -1;
        int end = -1;

        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) == '(')
                start = i;
            else if(exp.charAt(i) == ')'){
                end = i;
                break;
            }
        }

        if(start == -1 && end == -1)
            return Integer.parseInt(calcu(exp));
        else{
            String change = exp.substring(start + 1, end);
            String result = calcu(change);
            String cr = exp.substring(0, start) + result + exp.substring(end + 1);
            return run(cr);
        }
    }

    public String calcu(String exp) {
        List<String> expList = new ArrayList<>(Arrays.asList(exp.split(" ")));

        // 우선순위 (*, /) 먼저 처리
        for (int i = expList.size() - 1; i >= 0; i--) {
            if (expList.get(i).equals("*")) {
                int result = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                expList.set(i, result + "");
                expList.remove(i - 1);
                expList.remove(i); // 원래 i+1이었지만 제거 후 위치가 앞으로 이동함
                i--; // 연산 후 한 단계 앞에서 계속 진행
            } else if (expList.get(i).equals("/")) {
                int result = Integer.parseInt(expList.get(i - 1)) / Integer.parseInt(expList.get(i + 1));
                expList.set(i, result + "");
                expList.remove(i - 1);
                expList.remove(i);
                i--;
            }
        }

        // 나머지 연산 (+, -) 처리
        for (int i = 0; i < expList.size(); i++) {
            if (expList.get(i).equals("+")) {
                int result = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                expList.set(i, result + "");
                expList.remove(i - 1);
                expList.remove(i);
                i--;
            } else if (expList.get(i).equals("-")) {
                int result = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                expList.set(i, result + "");
                expList.remove(i - 1);
                expList.remove(i);
                i--;
            }
        }

        return expList.get(0);
    }
}
