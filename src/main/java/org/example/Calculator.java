package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public Number run(String s){
        List<String> parsed = parseInput(s);

        int idx = findFirstHighPriorityOperand(parsed);
        String op1 = parsed.get(idx-1);
        String operand = parsed.get(idx);
        String op2 = parsed.get(idx+1);

        Number number =  switch (operand) {
            case "+":
                yield Double.parseDouble(op1) + Double.parseDouble(op2);
            case "-":
                yield Double.parseDouble(op1) - Double.parseDouble(op2);
            case "*":
                yield Double.parseDouble(op1) * Double.parseDouble(op2);
            case "/":
                yield Double.parseDouble(op1) / Double.parseDouble(op2);
            default:
                throw new IllegalStateException("Unexpected value: " + operand);
        };
        if(parsed.size() == 3)
            return number;

        parsed.remove(idx-1);
        parsed.remove(idx-1);
        parsed.set(idx-1, String.valueOf(number));
        return run(String.join(" ", parsed));
    }

    private int findFirstHighPriorityOperand(List<String> parsed) {
        for(int idx = 0; idx < parsed.size(); idx++) {
            if (parsed.get(idx).equals("*") || parsed.get(idx).equals("/"))
                return idx;
        }
        return 1;
    }


    public List<String> parseInput(String s){
        String tmp = removeParentheses(s);
        return Arrays.stream(tmp.split(" ")).collect(Collectors.toList());
    }

    private static String removeParentheses(String s) {
        int startIdx =-1;
        int endIdx =-1;
        while(true){
            startIdx = s.lastIndexOf("(");
            endIdx = s.indexOf(")", startIdx);
            if(startIdx == -1 && endIdx == -1)
                break;

            Number res;

            res = run(s.substring(startIdx + 1, endIdx));
            s = s.substring(0, startIdx) +
                    res + s.substring(endIdx + 1);
        }
        return tmp;
    }


}
