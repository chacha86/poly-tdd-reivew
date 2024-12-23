public class Calc {
    public static int run(String s) {

        // (3+5) * 5
        s=s.replace(" ","");
        return execute(s);
    }

    private static int execute(String s) {
        if (s.startsWith("(")&&s.endsWith(")")) {
            return run(s.substring(1,s.length()-1));
        }

        int idx=findLowerPriority(s);
        if (idx != -1) {
            char op=s.charAt(idx);
            int left=execute(s.substring(0,idx));
            int right=execute(s.substring(idx+1));

            return calculate(left,right,op);
        }

        return Integer.parseInt(s);
    }


    // (3+5)*5+-4
    private static int findLowerPriority(String s) {
        int tmp=0;
        int min=Integer.MAX_VALUE;
        int minIdx=-1;

        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if (c=='(') tmp++;
            else if (c==')') tmp--;
            else if (tmp==0&&(c=='+'||c=='-'||c=='*'||c=='/')) {
                if (c=='-' &&(i==0||s.charAt(i-1)=='(')) {
                    continue;
                }
                int level = level(c);
                if (level<min) {
                    min=level;
                    minIdx=i;
                }
            }
        }


        return minIdx;
    }

    private static int level(char c) {
        if (c=='+'||c=='-') {
            return 1;
        }
        else return 2;
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
