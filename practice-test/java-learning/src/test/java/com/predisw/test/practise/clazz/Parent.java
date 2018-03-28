package com.predisw.test.practise.clazz;

public class Parent {

    public static String static_str = "only_static";

    static{
        System.out.println("parent's static block is loading");
    }

    {
        System.out.println("non static blocks in parent is initialization");
    }

}
