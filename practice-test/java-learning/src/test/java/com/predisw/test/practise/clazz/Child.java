package com.predisw.test.practise.clazz;

public class Child extends Parent{

    static {

        System.out.println("child's static block is loading");
    }

    {
        System.out.println("non static blocks in child is initialized");

    }
}
