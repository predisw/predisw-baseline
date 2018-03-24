package com.predisw.test.practise.clazz;

public class Parent {

    static{
        System.out.println("parent's static block is loading");
    }

    {
        System.out.println("non static blocks in parent is initialization");
    }

}
