package com.devcouse;

public class Main {
    public static void main(String[] args) {
        float rs = new Calc().run("((3 + 5) * 5 + -10) * 10 / 5");
        System.out.println(rs);
    }
}