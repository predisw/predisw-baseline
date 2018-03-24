package com.predisw.test.practise;

public class InvokestaticDemo {

    public static Integer add(Integer x, Integer y)  {
        return x + y;
    }

    public static String add(String x, String y)  {
        return x + y;
    }

    public static void main(String[] args) {
        add(2,3);
        add("2","3");
    }
}
