public class Calc {
    public static int run(String s) {
        s=s.replace(" ","");
        return execute(s);
    }

    /***
     * 코드 고려 사항
     * 1. '-' 처리 => 연산자인지 아닌지
     * 2. 동일 우선순위 -> +,- 가 연달아 나올떄 처리 방법
     * 3. 괄호 처리 방법
     * 재귀니까 우선순위 낮은것부터 처리해야함 (반대흐름)
     */

    private static int execute(String s) {
        if (s.startsWith("(")&&s.endsWith(")")) {
            return run(s.substring(1,s.length()-1));
        }

        int idx=findLowerPriority(s,'+','-');
        if (idx != -1) {
            char op=s.charAt(idx);
            int left=execute(s.substring(0,idx));
            int right=execute(s.substring(idx+1));
            return calculate(left,right,op);
        }

        idx=findLowerPriority(s,'*','/');
        if (idx != -1) {
            char op=s.charAt(idx);
            int left=execute(s.substring(0,idx));
            int right=execute(s.substring(idx+1));
            return calculate(left,right,op);
        }

        if (s.startsWith("-")) {
            return (-1)*execute(s.substring(1));
        }


        return Integer.parseInt(s);
    }

    private static int findLowerPriority(String s,char op1,char op2) {
        int depth=0;
        // 뒤에서 순회하는 이유는 좌->우 우선순위를 지키기 위해
        for (int i = s.length()-1; i >= 0 ; i--) {
            char c=s.charAt(i);
            if (c==')') depth++;
            else if (c=='(') depth--;
            else if ((depth==0) && (c==op1||c==op2)) {
                // 연산자가 아닌 경우 (맨 앞에 있거나 c 앞에 연산자가 있는 경우)
                if (c=='-'&&(i==0||"+-*/(".indexOf(s.charAt(i-1))!=-1)) {
                    continue;
                }
                return i;
            }
        }

        return -1;
    }



    private static int calculate(int left, int right, char operator) {
        int an=0;
        switch (operator) {
            case '+': an=left+right; break;
            case '-': an=left-right; break;
            case '*': an=left*right; break;
            case '/': an=left/right; break;
            default: break;
        }

        return an;
    }

}
